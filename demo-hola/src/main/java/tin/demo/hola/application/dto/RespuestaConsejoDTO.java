package tin.demo.hola.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RespuestaConsejoDTO {
    private String mensajeConsejo;
    private boolean alertaRiesgoCritico;
}
