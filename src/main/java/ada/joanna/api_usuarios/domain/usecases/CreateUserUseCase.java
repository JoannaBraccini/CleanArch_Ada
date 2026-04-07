package ada.joanna.api_usuarios.domain.usecases;

import ada.joanna.api_usuarios.domain.User;
import ada.joanna.api_usuarios.domain.repositories.UserRepository;
import ada.joanna.api_usuarios.infrastructure.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUserUseCase {

    @Autowired
    private UserRepository userRepository;

    public User execute(User user) {
        user.validateEmptyName();
        user.validateEmptyEmail();
        return UserMapper.toDomain(userRepository.save(UserMapper.toEntity(user)));
    }
}
