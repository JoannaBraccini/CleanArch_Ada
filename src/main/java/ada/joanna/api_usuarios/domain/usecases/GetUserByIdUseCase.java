package ada.joanna.api_usuarios.domain.usecases;

import ada.joanna.api_usuarios.domain.User;
import ada.joanna.api_usuarios.domain.repositories.UserRepository;
import ada.joanna.api_usuarios.infrastructure.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetUserByIdUseCase {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> execute(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDomain);
    }
}
