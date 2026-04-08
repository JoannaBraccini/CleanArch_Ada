package ada.joanna.api_usuarios.infrastructure.repositories;

import ada.joanna.api_usuarios.infrastructure.repositories.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryImplTest {

    private UserRepositoryImpl userRepositoryImpl;
    private JpaUserRepository jpaUserRepository;

    @BeforeEach
    void setUp() {
        jpaUserRepository = mock(JpaUserRepository.class);
        userRepositoryImpl = new UserRepositoryImpl(jpaUserRepository);
    }

    @Test
    void shouldFindAllUsers() {
        UserEntity user1 = new UserEntity(1L, "User 1", "user1@email.com");
        UserEntity user2 = new UserEntity(2L, "User 2", "user2@email.com");
        when(jpaUserRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<UserEntity> users = userRepositoryImpl.findAll();

        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals("User 1", users.get(0).getName());
        assertEquals("User 2", users.get(1).getName());
        verify(jpaUserRepository).findAll();
    }

    @Test
    void shouldFindUserById() {
        UserEntity user = new UserEntity(1L, "User 1", "user1@email.com");
        when(jpaUserRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        Optional<UserEntity> result = userRepositoryImpl.findById(1L);
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(jpaUserRepository).findById(1L);
    }

    @Test
    void shouldReturnEmptyOptionalWhenUserByIdDoesNotExist() {
        when(jpaUserRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<UserEntity> result = userRepositoryImpl.findById(99L);

        assertNotNull(result);
        assertEquals(Optional.empty(), result);
        verify(jpaUserRepository).findById(99L);
    }
}
