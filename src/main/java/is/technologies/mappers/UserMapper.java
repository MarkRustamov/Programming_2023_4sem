package is.technologies.mappers;

import is.technologies.dto.UserDTO;
import is.technologies.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(User user) {
        return new UserDTO(user.getUsername(), user.getPassword(), user.getPasswordConfirm(), user.getRole());
    }

    public User toUser(UserDTO userDTO) {
        return new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getPasswordConfirm(), userDTO.getRole());
    }
}
