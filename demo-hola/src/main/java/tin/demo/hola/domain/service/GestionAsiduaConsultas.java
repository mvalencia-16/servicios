package tin.demo.hola.domain.service;

import tin.demo.hola.domain.ports.ProveedorInteligenciaArtificial;
import tin.demo.hola.application.dto.RespuestaConsejoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GestionAsiduaConsultas {

    private final ProveedorInteligenciaArtificial proveedorIA;
    private final ConstructorInstruccionesBiblicas constructorPrompt;

    public RespuestaConsejoDTO procesarConsulta(UUID usuarioId, String pregunta, Boolean esCasado, Double ultimaNota, Double minimaNota, String indicadorMinimaNota) {
        // 1. Construir las instrucciones dinámicas del sistema
        String instruccionesSistema = constructorPrompt.prepararInstrucciones(esCasado, ultimaNota, minimaNota, indicadorMinimaNota, pregunta);

        // 2. Solicitar el consejo al adaptador de IA
        String consejoGenerado = proveedorIA.obtenerConsejoBiblico(instruccionesSistema, pregunta);

        // 3. Evaluar si activamos bandera de riesgo en la respuesta JSON
        boolean hayRiesgoDetectado = pregunta.toLowerCase().matches(".*(golpe|pegar|matar|amenaza|miedo|abuso|manipulacion).*");
        
        return new RespuestaConsejoDTO(consejoGenerado, hayRiesgoDetectado);
    }
}