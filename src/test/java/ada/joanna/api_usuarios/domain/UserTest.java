package ada.joanna.api_usuarios.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserTest {

    @Test
    void validateEmptyNameShouldThrowExceptionWhenNameIsNull() {
        User user = new User(1L, null, "test@email.com");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                user::validateEmptyName,
                "Expected validateEmptyName to throw, but it didn't"
        );

        assertEquals("Invalid name value", exception.getMessage());
    }

    @Test
    void validateEmptyNameShouldThrowExceptionWhenNameIsEmpty() {
        User user = new User(1L, "", "test@email.com");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                user::validateEmptyName,
                "Expected validateEmptyName to throw, but it didn't"
        );

        assertEquals("Invalid name value", exception.getMessage());
    }

    @Test
    void validateEmptyNameShouldNotThrowExceptionWhenNameIsValid() {
        User user = new User(1L, "Valid Name", "test@email.com");

        assertDoesNotThrow(user::validateEmptyName);
    }

    @Test
    void validateEmptyEmailShouldThrowExceptionWhenEmailIsNull() {
        User user = new User(1L, "Test Name", null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                user::validateEmptyEmail,
                "Expected validateEmptyEmail to throw, but it didn't"
        );

        assertEquals("Invalid email value", exception.getMessage());
    }

    @Test
    void validateEmptyEmailShouldNotThrowExceptionWhenEmailIsEmpty() {
        User user = new User(1L, "Test Name", "");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                user::validateEmptyEmail,
                "Expected validateEmptyEmail to throw, but it didn't"
        );

        assertEquals("Invalid email value", exception.getMessage());
    }

    @Test
    void validateEmptyEmailShouldNotThrowExceptionWhenEmailIsValid() {
        User user = new User(1L, "Test Name", "valid@email.com");

        assertDoesNotThrow(user::validateEmptyEmail);
    }
}
