package tin.demo.hola.domain.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConstructorInstruccionesBiblicas {

    public String prepararInstrucciones(boolean esMatrimonioCatolico, double notaActual, Double minimaNota, String indicadorMinimaNota, String preguntaUsuario) {
        String rutaConsulta="Inicio";
        String generoUsuario="mujer";
        String generoOrientacion="";

        StringBuilder instrucciones = new StringBuilder();

        //instrucciones.append("Eres un Asistente Espiritual Católico basado en la vida de Jesús y en la biblia catolica. ");
        //instrucciones.append("Eres un Consejero Espiritual Católico basado en la vida de Jesús y en la biblia catolica. ");
        instrucciones.append("Eres un Consejero Espiritual Católico, inspirado en la vida y enseñanzas de Jesucristo y fundamentado en la Sagrada Escritura (traducción de la Biblia Latinoamericana, edición católica). Tu misión es brindar orientación espiritual, apoyo emocional y consejo práctico desde una perspectiva católica, siempre con caridad, empatía y sabiduría pastoral. **Tono pastoral:** Responde con calidez, cercanía y respeto. Adapta tu lenguaje al nivel del consultante, evitando tecnicismos innecesarios y usando un tono compasivo y alentador. **Recuerda:** No eres un psicólogo ni un sustituto de la ayuda profesional. Eres un guía espiritual que acompaña desde la fe católica, señalando siempre el camino hacia Jesús, que es el Camino, la Verdad y la Vida. Todas las citas de la Escritura que proporciones deben ser tomadas exclusivamente de la traducción de la Biblia Latinoamericana (edición católica). ");
        instrucciones.append("REGLA DE ORO: Tu respuesta no debe exceder las 4 líneas de texto. ");
        //instrucciones.append("REGLA DE CIERRE: Incluye siempre una cita bíblica al azar (Libro Cap:Ver) al final. ");
        //instrucciones.append("REGLA DE CIERRE: Incluye siempre una cita bíblica al azar (Libro Cap:Ver:texto), debe ser una linea parte final, donde primer es la cita biblica y seguido del texto. ");
        //instrucciones.append("REGLA DE CIERRE: Al final, en una línea aparte, incluir SIEMPRE una cita bíblica con este formato exacto: (Libro Cap:Ver) \"texto completo de la cita\". Ejemplo: (1 Juan 4:16) \"El que permanece en el amor, y Dios en él\". NUNCA escribir el texto antes de la referencia. ");

        //Genero en mensaje de respuesta:
        generoOrientacion = "**GÉNERO DEL USUARIO:** Tu respuesta, EXCLUIR cualquier palabra de inicio que haga referencia a un genero de suario. Ejemplo: Hijo o Hija. ";
        instrucciones.append(generoOrientacion);
        /* 
        switch (generoUsuario.toLowerCase().trim()) {
            case "mujer":
                generoOrientacion = "**GÉNERO DEL USUARIO:** Tu respuesta, debe usar el término adecuado según el género. Ejemplo: si es \"mujer\" usa Hija";
                instrucciones.append(generoOrientacion);
                log.info("Usa genero={} ", generoUsuario);
                break;
            case "hombre":
                generoOrientacion = "**GÉNERO DEL USUARIO:** Tu respuesta, debe usar el término adecuado según el género. Ejemplo: si es \"mujer\" usa Hijo";
                instrucciones.append(generoOrientacion);
                log.info("Usa genero={} ", generoUsuario);
                break;
        }
        */

        if (textoContieneViolencia(preguntaUsuario)) {
            return "MODO EMERGENCIA: Has detectado violencia o maltrato. Prioriza la seguridad física. " +
                   "Indica firmemente que la vida es sagrada y debe buscar ayuda externa o refugio de inmediato. " +
                   "No pidas paciencia ni sumisión bajo ninguna circunstancia.";
        }

        if (notaActual < 3.5) {
            if (esMatrimonioCatolico) {
                instrucciones.append("CONTEXTO: Matrimonio sagrado en permanente crisis. Prohibido sugerir separación. ");
                instrucciones.append("Enfócate en la oración, el perdón mutuo y acudir a consejería sacerdotal.");
                rutaConsulta="Menos 3.5 - C";
            } else {
                instrucciones.append("CONTEXTO: Relación no sacramental en permanente crisis. ");
                instrucciones.append("Si hay sufrimiento constante y violento, sugiere una separación sana y en paz, de forma inmediata. ");
                rutaConsulta="Menos 3.5 - R";
            }
        } else if (notaActual >= 3.5 && notaActual < 5.0) {
            if (esMatrimonioCatolico) {
                instrucciones.append("CONTEXTO: Matrimonio sagrado con primeras crisis. Prohibido sugerir separación. ");
                instrucciones.append("Enfócate en la oración, el perdón mutuo y acudir a consejería sacerdotal.");
                rutaConsulta="3.5 - 5.0 - C";
            } else {
                instrucciones.append("CONTEXTO: Relación no sacramental en crisis. ");
                instrucciones.append("Si hay sufrimiento constante, sugiere una separación sana y en paz.");
                rutaConsulta="3.5 - 5.0 - R";
            }
        } else if (notaActual >= 5.0 && notaActual < 6.0) {
            instrucciones.append("CONTEXTO: Relación estancada. Da aliento para salir de la monotonía.");
            rutaConsulta="5.0 - 6.0 - C";
        } else if (notaActual >= 6.0 && notaActual < 7.0) {
            //Posible Pre-matrimonio
            instrucciones.append("CONTEXTO: Relación con altibajos. Anima a fortalecer la comunicación y la fe compartida.");
            rutaConsulta="6.0 - 7.0 - C";
        } else {
            //Pensar en Matrimonio | Y para Casados acercarse a consagracion de Jesus Sacramentado.
            instrucciones.append("CONTEXTO: Relación estable. Guía hacia la excelencia y la imitación de Cristo.");
            rutaConsulta="7.0 - 10.0 - C/R";
        }
        
        if (minimaNota != null && indicadorMinimaNota != null && !indicadorMinimaNota.isBlank()) {
            instrucciones.append(generarInstruccionPuntoDebil(minimaNota, indicadorMinimaNota));
        }

        log.info("rutaConsulta={} | minimaNota={} | indicadorMinimaNota={}", rutaConsulta, minimaNota, indicadorMinimaNota);
        
        instrucciones.append("REGLA DE CIERRE: Al final, en una nueva linea a parte como nuevo parrago, incluir SIEMPRE una cita bíblica diferente para cada RESPUESTA, con este formato exacto: (Libro Cap:Ver) \"texto completo de la cita\". Ejemplo: (1 Juan 4:16) \"El que permanece en el amor, y Dios en él\". NUNCA escribir el texto antes de la referencia. ");

        return instrucciones.toString();
    }

    private String generarInstruccionPuntoDebil(double minimaNota, String indicador) {
        String nivelCrisis;
        String orientacion;

        if (minimaNota <= 3) {
            nivelCrisis = "en crisis severa";
        } else if (minimaNota <= 5) {
            nivelCrisis = "deteriorado";
        } else if (minimaNota <= 7) {
            nivelCrisis = "por mejorar";
        } else {
            nivelCrisis = "con oportunidad de excelencia";
        }

        switch (indicador.toLowerCase().trim()) {
            case "comunicación":
            case "comunicacion":
                orientacion = minimaNota <= 5
                    ? "Sugiere escucharse en oración juntos y hablar con el corazón abierto como lo hacían los discípulos con Jesús."
                    : "Anima a profundizar el diálogo desde la fe y la escucha activa.";
                break;
            case "paciencia":
                orientacion = minimaNota <= 5
                    ? "Recuerda que la paciencia es fruto del Espíritu Santo; invita a pedir este don en oración."
                    : "Alienta a practicar la mansedumbre que Cristo enseña con el ejemplo.";
                break;
                //"Recuerda que la paciencia es fruto del Espíritu Santo (Gal 5:22); invita a pedir este don en oración."
            case "perdón":
            case "perdon":
                orientacion = minimaNota <= 5
                    ? "El perdón es central en el Evangelio; invita a reconciliarse como Cristo perdonó desde la Cruz."
                    : "Anima a soltar rencores pequeños antes de que se conviertan en heridas profundas.";
                break;
                //"El perdón es central en el Evangelio; invita a reconciliarse como Cristo perdonó desde la Cruz (Lc 23:34)."
            case "independencia":
                orientacion = minimaNota <= 5
                    ? "Recuerda que cada persona es templo del Espíritu Santo; el amor sano respeta la libertad del otro."
                    : "Sugiere cultivar proyectos personales que enriquezcan la relación sin generar dependencia.";
                break;
            case "ayuda mutua":
                orientacion = minimaNota <= 5
                    ? "El servicio mutuo es el corazón del amor cristiano; invita a preguntarse '¿en qué puedo servirte hoy?'."
                    : "Anima a descubrir nuevas formas concretas de apoyarse en el día a día.";
                break;
                //"El servicio mutuo es el corazón del amor cristiano (Jn 13:14); invita a preguntarse..."
            default:
                orientacion = "Aborda este aspecto con oración, diálogo sincero y apoyo mutuo desde la fe.";
        }

        log.info("Generacion Orientacion:{}",  orientacion);

        return String.format(" PUNTO DÉBIL (%s, nota %.1f): El indicador '%s' está %s. %s",
                indicador, minimaNota, indicador, nivelCrisis, orientacion);
    }

    private boolean textoContieneViolencia(String texto) {
        return texto.toLowerCase().matches(".*(golpe|pegar|matar|amenaza|miedo|abuso).*");
    }
}