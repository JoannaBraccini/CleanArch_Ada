package ada.joanna.api_usuarios.infrastructure.repositories;

import ada.joanna.api_usuarios.infrastructure.repositories.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
