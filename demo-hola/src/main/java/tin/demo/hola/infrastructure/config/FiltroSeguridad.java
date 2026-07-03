package tin.demo.hola.infrastructure.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class FiltroSeguridad extends OncePerRequestFilter {

    private static final String HEADER_API_KEY = "X-App-Key";

    @Value("${app.api-key}")
    private String apiKeyEsperada;

    @Value("${app.rate-limit.peticiones-por-minuto:20}")
    private int peticionesPorMinuto;

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Dejar pasar preflight OPTIONS (necesario para CORS) y cualquier otra ruta
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        // Aplicar solo al endpoint protegido
        return !request.getRequestURI().contains("/realizar-pregunta");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // ── Opción 1: Validar API Key ──────────────────────────────────────
        String apiKeyRecibida = request.getHeader(HEADER_API_KEY);
        if (apiKeyRecibida == null || !apiKeyRecibida.equals(apiKeyEsperada)) {
            log.warn("[Seguridad] API Key inválida o ausente desde IP={}", obtenerIp(request));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"No autorizado\"}");
            return;
        }

        // ── Opción 3: Rate Limiting por IP ────────────────────────────────
        String ip = obtenerIp(request);
        Bucket bucket = buckets.computeIfAbsent(ip, this::crearBucket);

        if (!bucket.tryConsume(1)) {
            log.warn("[Seguridad] Rate limit alcanzado para IP={}", ip);
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Demasiadas solicitudes. Intenta en un momento.\"}");
            return;
        }

        log.debug("[Seguridad] Acceso permitido para IP={} | tokens restantes={}", ip, bucket.getAvailableTokens());
        filterChain.doFilter(request, response);
    }

    private Bucket crearBucket(String ip) {
        Bandwidth limite = Bandwidth.builder()
                .capacity(peticionesPorMinuto)
                .refillGreedy(peticionesPorMinuto, Duration.ofMinutes(1))
                .build();
        return Bucket.builder().addLimit(limite).build();
    }

    private String obtenerIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isBlank()) {
            return forwarded.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}
