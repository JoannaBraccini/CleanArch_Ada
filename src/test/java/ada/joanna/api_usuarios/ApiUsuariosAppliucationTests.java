package ada.joanna.api_usuarios;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mockStatic;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ApiUsuariosAppliucationTests {

    @Test
    void main_shouldStartApplication() {
        try (var mockedSpringApplication = mockStatic(SpringApplication.class)) {
            ApiUsuariosApplication.main(new String[]{});
            mockedSpringApplication.verify(() -> SpringApplication.run(ApiUsuariosApplication.class, new String[]{}));
        }
    }
}
