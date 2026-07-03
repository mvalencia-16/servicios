package tin.demo.hola.application.rest;

import tin.demo.hola.domain.service.GestionAsiduaConsultas;
import tin.demo.hola.application.dto.SolicitudPreguntaDTO;
import tin.demo.hola.application.dto.RespuestaConsejoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/asesor-espiritual")
@RequiredArgsConstructor
public class AsesorComunitarioController {

    private final GestionAsiduaConsultas gestionAsiduaConsultas;

    @PostMapping("/realizar-pregunta")
    public ResponseEntity<RespuestaConsejoDTO> recibirPregunta(
            @RequestHeader("X-Usuario-ID") UUID usuarioId,
            @RequestBody SolicitudPreguntaDTO solicitud) {

        log.info("[realizar-pregunta] X-Usuario-ID={} | idUsuario={} | esCasado={} | ultimaNota={} | minimaNota={} | indicadorMinimaNota='{}' | pregunta='{}'",
                usuarioId,
                solicitud.getIdUsuario(),
                solicitud.getEsCasado(),
                solicitud.getUltimaNota(),
                solicitud.getMinimaNota(),
                solicitud.getIndicadorMinimaNota(),
                solicitud.getPregunta());

        RespuestaConsejoDTO respuesta = gestionAsiduaConsultas.procesarConsulta(usuarioId, solicitud.getPregunta(), solicitud.getEsCasado(), solicitud.getUltimaNota(), solicitud.getMinimaNota(), solicitud.getIndicadorMinimaNota());

        log.info("[realizar-pregunta] alertaRiesgoCritico={}", respuesta.isAlertaRiesgoCritico());

        return ResponseEntity.ok(respuesta);
    }
}

