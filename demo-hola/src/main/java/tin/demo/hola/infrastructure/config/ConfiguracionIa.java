package tin.demo.hola.infrastructure.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import javax.net.ssl.SSLContext;
import java.security.KeyStore;

@Configuration
public class ConfiguracionIa {

    @Bean
    public RestClient clienteRestDeepSeek(
            @Value("${ia.deepseek.url:https://api.deepseek.com/v1}") String urlBase,
            @Value("${ia.deepseek.api-key}") String apiKey) throws Exception {

        // En Windows usa el almacén de certificados del SO (tiene los CAs actualizados).
        // En Linux/Docker usa el truststore por defecto de la JVM.
        SSLContext sslContext;
        if (System.getProperty("os.name", "").toLowerCase().contains("win")) {
            KeyStore windowsStore = KeyStore.getInstance("Windows-ROOT");
            windowsStore.load(null, null);
            sslContext = SSLContextBuilder.create()
                    .loadTrustMaterial(windowsStore, null)
                    .build();
        } else {
            sslContext = SSLContext.getDefault();
        }

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(
                    PoolingHttpClientConnectionManagerBuilder.create()
                        .setSSLSocketFactory(
                            SSLConnectionSocketFactoryBuilder.create()
                                .setSslContext(sslContext)
                                .build())
                        .build())
                .build();

        return RestClient.builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory(httpClient))
                .baseUrl(urlBase)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}