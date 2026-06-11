package tin.demo.hola;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "${cors.allowed-origins:http://localhost:5173}")
@RestController
public class hola {
    
    @RequestMapping("/")
    public String holasaludo(){

        return "Hola a todos TIN";
    }

    @RequestMapping("/saludo1")
    public String saludo1(){

        return "Saludo 1: Hola a todos TIN";
    }

    @RequestMapping("/saludo2")
    public String saludo2(){

        return "Saludo 2: Hola a todos TIN";
    }
}
