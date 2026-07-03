package tin.demo.hola.infrastructure.adapters.ai;

import tin.demo.hola.domain.ports.ProveedorInteligenciaArtificial;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AdaptadorDeepSeek implements ProveedorInteligenciaArtificial {

    private final RestClient clienteRestDeepSeek;
    private final String modeloConfigurado;

    public AdaptadorDeepSeek(RestClient clienteRestDeepSeek, 
                             @Value("${ia.deepseek.modelo:deepseek-chat}") String modeloConfigurado) {
        this.clienteRestDeepSeek = clienteRestDeepSeek;
        this.modeloConfigurado = modeloConfigurado;
    }

    @Override
    public String obtenerConsejoBiblico(String instruccionesSistema, String preguntaUsuario) {
        try {
            Map<String, Object> cuerpoPeticion = Map.of(
                "model", modeloConfigurado,
                "messages", List.of(
                    Map.of("role", "system", "content", instruccionesSistema),
                    Map.of("role", "user", "content", preguntaUsuario)
                ),
                "temperature", 0.7,
                "max_tokens", 150
            );

            Map<?, ?> respuestaJson = clienteRestDeepSeek.post()
                    .uri("/chat/completions")
                    .body(cuerpoPeticion)
                    .retrieve()
                    .body(Map.class);

            log.info("[DeepSeek] Respuesta obtenida: '{}'", respuestaJson);
            log.info("[DeepSeek] Texto extraído: '{}'", extraerTextoDeRespuesta(respuestaJson));
            
            // Log de tokens de uso
            if (respuestaJson != null && respuestaJson.containsKey("usage")) {
                Map<?, ?> uso = (Map<?, ?>) respuestaJson.get("usage");
                Object promptTokens         = uso.get("prompt_tokens");
                Object cacheHitTokens       = uso.get("prompt_cache_hit_tokens");
                Object cacheMissTokens      = uso.get("prompt_cache_miss_tokens");
                log.info("[DeepSeek] Tokens — prompt_tokens={} | prompt_cache_hit_tokens={} | prompt_cache_miss_tokens={}",
                        promptTokens, cacheHitTokens, cacheMissTokens);
            }

            return extraerTextoDeRespuesta(respuestaJson);

        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con el Asesor de IA: " + e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    private String extraerTextoDeRespuesta(Map<?, ?> respuestaJson) {
        if (respuestaJson != null && respuestaJson.containsKey("choices")) {
            List<Map<String, Object>> opciones = (List<Map<String, Object>>) respuestaJson.get("choices");
            if (!opciones.isEmpty()) {
                Map<String, Object> primeraOpcion = opciones.get(0);
                Map<String, Object> mensaje = (Map<String, Object>) primeraOpcion.get("message");
                if (mensaje != null) {
                    return (String) mensaje.get("content");
                }
            }
        }
        throw new RuntimeException("Estructura de respuesta inesperada de DeepSeek.");
    }
}