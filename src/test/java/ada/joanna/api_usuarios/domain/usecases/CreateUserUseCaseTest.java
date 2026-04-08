package ada.joanna.api_usuarios.domain.usecases;

import ada.joanna.api_usuarios.domain.User;
import ada.joanna.api_usuarios.domain.repositories.UserRepository;
import ada.joanna.api_usuarios.infrastructure.repositories.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;


    @Test
    void shouldCreateUserWithSuccess() {
        User user = mock(User.class);
        when(userRepository.save(any())).thenReturn(mock(UserEntity.class));

        doNothing().when(user).validateEmptyName();
        doNothing().when(user).validateEmptyEmail();

        User result = createUserUseCase.execute(user);

        assertNotNull(result);
        verify(user).validateEmptyName();
        verify(user).validateEmptyEmail();
        verify(userRepository).save(any());
    }

    @Test
    void shouldCreateUserThrowsValidationNameError() {
        User user = new User(1L, "", "");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createUserUseCase.execute(user)
        );

        assertEquals("Invalid name value", exception.getMessage());

        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldCreateUserThrowsValidationEmailError() {
        User user = new User(1L, "Test", "");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createUserUseCase.execute(user)
        );

        assertEquals("Invalid email value", exception.getMessage());

        verify(userRepository, never()).save(any());
    }
}
