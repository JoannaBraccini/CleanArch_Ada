package ada.joanna.api_usuarios.services;

import ada.joanna.api_usuarios.model.User;
import ada.joanna.api_usuarios.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() { return userRepository.findAll(); }

    public User findUserById(Long id) { return userRepository.findById(id).orElse(null); }

    public User save(User user) { return userRepository.save(user); }

    public void delete(Long id) { userRepository.deleteById(id); }
}
