package ada.joanna.api_usuarios.domain.usecases;

import ada.joanna.api_usuarios.domain.User;
import ada.joanna.api_usuarios.domain.repositories.UserRepository;
import ada.joanna.api_usuarios.infrastructure.repositories.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetUsersUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUsersUseCase getUsersUseCase;


    @Test
    void shouldReturnListOfUsersWhenRepositoryHasUsers() {
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setId(1L);
        userEntity1.setName("User 1");
        userEntity1.setEmail("test1@email.com");

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setId(2L);
        userEntity2.setName("User 2");
        userEntity2.setEmail("test2@email.com");

        when(userRepository.findAll()).thenReturn(Arrays.asList(userEntity1, userEntity2));

        List<User> result = getUsersUseCase.execute();

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals("User 1", result.get(0).getName());
        assertEquals("test1@email.com", result.get(0).getEmail());

        assertEquals("User 2", result.get(1).getName());
        assertEquals("test2@email.com", result.get(1).getEmail());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenRepositoryHasNoUsers() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        List<User> result = getUsersUseCase.execute();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(userRepository, times(1)).findAll();
    }
}
