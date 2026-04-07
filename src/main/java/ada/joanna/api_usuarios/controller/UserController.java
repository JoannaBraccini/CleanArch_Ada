package ada.joanna.api_usuarios.controller;

import ada.joanna.api_usuarios.model.User;
import ada.joanna.api_usuarios.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() { return userService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping
    public User createUser(@RequestBody User user) { return userService.save(user); }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) { userService.delete(id); }
}