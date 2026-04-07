package ada.joanna.api_usuarios.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {

    private Long id;
    private String name;
    private String email;

    public void validateEmptyName() {
        if (this.name == null || this.name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name value");
        }
    }

    public void validateEmptyEmail() {
        if (this.email == null || this.email.isEmpty()) {
            throw new IllegalArgumentException("Invalid email value");
        }
    }
}
