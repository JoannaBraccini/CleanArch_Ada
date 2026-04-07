package ada.joanna.api_usuarios.domain.usecases;

import ada.joanna.api_usuarios.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserUseCase {

    @Autowired
    private UserRepository userRepository;

    public void execute(Long id) {userRepository.deleteById(id); }
}
