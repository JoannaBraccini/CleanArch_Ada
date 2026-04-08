package ada.joanna.api_usuarios.infrastructure;

import ada.joanna.api_usuarios.domain.User;
import ada.joanna.api_usuarios.infrastructure.mapper.UserMapper;
import ada.joanna.api_usuarios.infrastructure.repositories.entities.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void shouldConvertUserEntityToDomain() {
        UserEntity entity = new UserEntity(1L, "Entity Name", "entity@email.com");

        User user = UserMapper.toDomain(entity);

        assertNotNull(user);
        assertEquals(entity.getId(), user.getId());
        assertEquals(entity.getName(), user.getName());
        assertEquals(entity.getEmail(), user.getEmail());
    }

    @Test
    void shouldConvertDomainToUserEntity() {
        User user = new User(2L, "Domain Name", "domain@email.com");

        UserEntity entity = UserMapper.toEntity(user);

        assertNotNull(entity);
        assertEquals(user.getId(), entity.getId());
        assertEquals(user.getName(), entity.getName());
        assertEquals(user.getEmail(), entity.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenUserEntityIsNullOnToDomain() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> UserMapper.toDomain(null));

        assertEquals("UserEntity cannot be null when mapping to domain", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUserIsNullOnToEntity() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> UserMapper.toEntity(null));

        assertEquals("User domain object cannot be null when mapping to entity", exception.getMessage());
    }
}
