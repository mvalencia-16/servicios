package tin.demo.hola;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//APlicacion se ejecuta en Puerto:8088
@SpringBootApplication
public class HolaApplication {

	public static void main(String[] args) {

        SpringApplication.run(HolaApplication.class, args);
	}

}
