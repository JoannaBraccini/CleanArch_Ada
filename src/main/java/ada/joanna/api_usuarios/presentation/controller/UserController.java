package ada.joanna.api_usuarios.presentation.controller;

import ada.joanna.api_usuarios.domain.User;
import ada.joanna.api_usuarios.domain.usecases.CreateUserUseCase;
import ada.joanna.api_usuarios.domain.usecases.DeleteUserUseCase;
import ada.joanna.api_usuarios.domain.usecases.GetUserByIdUseCase;
import ada.joanna.api_usuarios.domain.usecases.GetUsersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private GetUserByIdUseCase getUserById;

    @Autowired
    private CreateUserUseCase createUser;

    @Autowired
    private GetUsersUseCase getUsers;

    @Autowired
    private DeleteUserUseCase deleteUser;

    @GetMapping
    public List<User> getAllUsers() {
        return getUsers.execute();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> user = getUserById.execute(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return createUser.execute(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        deleteUser.execute(id);
    }
}