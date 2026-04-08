package ada.joanna.api_usuarios.domain.usecases;

import ada.joanna.api_usuarios.domain.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DeleteUserUseCase deleteUserUseCase;


    @Test
    void shouldDeleteUserByIdWithSuccess() {
        Long userId = 1L;

        doNothing().when(userRepository).deleteById(userId);

        deleteUserUseCase.execute(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void shouldThrowExceptionWhenRepositoryFails() {
        Long userId = 1L;

        doThrow(new RuntimeException("Error deleting user")).when(userRepository).deleteById(userId);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> deleteUserUseCase.execute(userId)
        );

        assertEquals("Error deleting user", exception.getMessage());
    }
}
