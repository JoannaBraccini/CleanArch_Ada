package ada.joanna.api_usuarios.infrastructure.repositories;

import ada.joanna.api_usuarios.domain.repositories.UserRepository;
import ada.joanna.api_usuarios.infrastructure.repositories.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    JpaUserRepository jpaUserRepository;

    @Override
    public List<UserEntity> findAll() {
        return jpaUserRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return jpaUserRepository.findById(id);
    }

    @Override
    public UserEntity save(UserEntity user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        jpaUserRepository.deleteById(id);
    }
}
