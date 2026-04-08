package ada.joanna.api_usuarios.infrastructure.mapper;

import ada.joanna.api_usuarios.domain.User;
import ada.joanna.api_usuarios.infrastructure.repositories.entities.UserEntity;
import java.util.Objects;

public class UserMapper {

    public static User toDomain(UserEntity entity) {
        Objects.requireNonNull(entity, "UserEntity cannot be null when mapping to domain");
        return new User(entity.getId(), entity.getName(), entity.getEmail());
    }

    public static UserEntity toEntity(User domain) {
        Objects.requireNonNull(domain, "User domain object cannot be null when mapping to entity");
        return new UserEntity(domain.getId(), domain.getName(), domain.getEmail());
    }
}
