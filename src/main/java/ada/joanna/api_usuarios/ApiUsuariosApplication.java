package ada.joanna.api_usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ada.joanna.api_usuarios")
public class ApiUsuariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiUsuariosApplication.class, args);
    }
}
