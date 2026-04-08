package ada.joanna.api_usuarios.presentation.controller;

import ada.joanna.api_usuarios.domain.User;
import ada.joanna.api_usuarios.domain.usecases.CreateUserUseCase;
import ada.joanna.api_usuarios.domain.usecases.DeleteUserUseCase;
import ada.joanna.api_usuarios.domain.usecases.GetUserByIdUseCase;
import ada.joanna.api_usuarios.domain.usecases.GetUsersUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private GetUsersUseCase getUsersUseCase;

    @Mock
    private GetUserByIdUseCase getUserByIdUseCase;

    @Mock
    private CreateUserUseCase createUserUseCase;

    @Mock
    private DeleteUserUseCase deleteUserUseCase;


    @Test
    void shouldReturnAllUsers() {
        List<User> users = Arrays.asList(
                new User(1L, "User 1", "user1@email.com"),
                new User(2L, "User 2", "user2@email.com")
        );
        when(getUsersUseCase.execute()).thenReturn(users);

        List<User> result = userController.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("User 1", result.get(0).getName());
        verify(getUsersUseCase).execute();
    }

    @Test
    void shouldReturnUserByIdIfExists() {
        User user = new User(1L, "User 1", "user1@email.com");
        when(getUserByIdUseCase.execute(1L)).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.getUser(1L);

        assertNotNull(response);
        assertEquals(ResponseEntity.ok(user), response);
        verify(getUserByIdUseCase).execute(1L);
    }

    @Test
    void shouldReturnNotFoundWhenUserByIdDoesNotExist() {
        when(getUserByIdUseCase.execute(1L)).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.getUser(1L);

        assertNotNull(response);
        assertEquals(ResponseEntity.notFound().build(), response);
        verify(getUserByIdUseCase).execute(1L);
    }

    @Test
    void shouldCreateUser() {
        User user = new User(null, "New User", "test@email.com");
        when(createUserUseCase.execute(user)).thenReturn(user);

        User createdUser = userController.createUser(user);

        assertNotNull(createdUser);
        assertEquals(user, createdUser);
        verify(createUserUseCase).execute(user);
    }

    @Test
    void shouldDeleteUserById() {
        Long userId = 1L;

        userController.deleteUser(userId);

        verify(deleteUserUseCase).execute(userId);
    }

    @Test
    void shouldPropagateExceptionWhenDeleteFails() {
        Long userId = 1L;
        doThrow(new RuntimeException("Error deleting user")).when(deleteUserUseCase).execute(userId);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userController.deleteUser(userId));

        assertEquals("Error deleting user", exception.getMessage());
        verify(deleteUserUseCase).execute(userId);
    }
}
