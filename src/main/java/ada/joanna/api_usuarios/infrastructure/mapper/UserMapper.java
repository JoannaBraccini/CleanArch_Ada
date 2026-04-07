package ada.joanna.api_usuarios.infrastructure.mapper;

import ada.joanna.api_usuarios.domain.User;
import ada.joanna.api_usuarios.infrastructure.repositories.entities.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity entity) {
        return new User(entity.getId(), entity.getName(), entity.getEmail());
    }

    public static UserEntity toEntity(User domain) {
        return new UserEntity(domain.getId(), domain.getName(), domain.getEmail());
    }
}
