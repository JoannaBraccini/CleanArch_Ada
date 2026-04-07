package ada.joanna.api_usuarios.domain.usecases;

import ada.joanna.api_usuarios.domain.User;
import ada.joanna.api_usuarios.domain.repositories.UserRepository;
import ada.joanna.api_usuarios.infrastructure.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetUsersUseCase {

    @Autowired
    private UserRepository userRepository;

    public List<User> execute() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDomain)
                .collect(Collectors.toList());
    }
}
