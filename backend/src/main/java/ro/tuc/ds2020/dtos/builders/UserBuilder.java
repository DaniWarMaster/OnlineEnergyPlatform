package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.UserDTO;
import ro.tuc.ds2020.entities.Users;

public class UserBuilder {
    public UserBuilder() {

    }

    public static UserDTO toUserDTO(Users user) {
        return new UserDTO(
                user.getId(),
                user.isAdmin(),
                user.getName(),
                user.getAddress(),
                user.getBirthDate(),
                user.getPassword(),
                user.getDevices()
        );
    }

    public static Users toEntity(UserDTO userDTO) {
        return new Users(
                userDTO.getName(),
                userDTO.getAddress(),
                userDTO.getBirthDate(),
                userDTO.isAdmin(),
                userDTO.getPassword()
        );
    }
}
