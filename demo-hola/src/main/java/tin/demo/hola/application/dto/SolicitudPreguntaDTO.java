package tin.demo.hola.application.dto;

import lombok.Data;

@Data
public class SolicitudPreguntaDTO {
    private String pregunta;
    private String idUsuario;
    private Boolean esCasado;
    private Double ultimaNota;
    private Double minimaNota;
    private String indicadorMinimaNota;
}