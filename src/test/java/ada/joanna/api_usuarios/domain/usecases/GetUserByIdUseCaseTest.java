package ada.joanna.api_usuarios.domain.usecases;

import ada.joanna.api_usuarios.domain.User;
import ada.joanna.api_usuarios.domain.repositories.UserRepository;
import ada.joanna.api_usuarios.infrastructure.repositories.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUserByIdUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUserByIdUseCase getUserByIdUseCase;


    @Test
    void shouldReturnUserWhenIdExists() {
        Long userId = 1L;

        UserEntity userEntity = new UserEntity();

        userEntity.setId(userId);
        userEntity.setName("Joanna");
        userEntity.setEmail("test@email.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        Optional<User> result = getUserByIdUseCase.execute(userId);

        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getId());
        assertEquals("Joanna", result.get().getName());
        assertEquals("test@email.com", result.get().getEmail());
        verify(userRepository).findById(userId);
    }

    @Test
    void shouldReturnEmptyOptionalWhenIdDoesNotExist() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        Optional<User> result = getUserByIdUseCase.execute(userId);

        assertTrue(result.isEmpty());
        verify(userRepository).findById(userId);
    }
}
