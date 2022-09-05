package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.SensorsDTO;
import ro.tuc.ds2020.dtos.UserDTO;
import ro.tuc.ds2020.dtos.builders.SensorsBuilder;
import ro.tuc.ds2020.dtos.builders.UserBuilder;
import ro.tuc.ds2020.entities.Sensors;
import ro.tuc.ds2020.entities.Users;
import ro.tuc.ds2020.repositories.SensorRepository;
import ro.tuc.ds2020.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    public List<UserDTO> findUsers() {
        List<Users> personList = userRepository.findAll();
        return personList.stream()
                .map(UserBuilder::toUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findUserById(Integer id) {
        Optional<Users> prosumerOptional = userRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("User with id {} was not found in db", id);
            throw new ResourceNotFoundException(Sensors.class.getSimpleName() + " with id: " + id);
        }
        return UserBuilder.toUserDTO(prosumerOptional.get());
    }

    public UserDTO findUserByNameAndPassword(String name, String password) {
        Users user = userRepository.findByNameAndPassword(name, password);
        return UserBuilder.toUserDTO(user);
    }

    public int insert(UserDTO userDTO) {
        Users user = UserBuilder.toEntity(userDTO);
        user = userRepository.save(user);
        LOGGER.debug("User with id {} was inserted in db", user.getId());
        return user.getId();
    }

    public int update(UserDTO userDTO) {
        Users user = UserBuilder.toEntity(userDTO);
        user.setId(userDTO.getId());
        user.setDevices(userDTO.getDevices());

        userRepository.save(user);
        LOGGER.debug("Sensor with id {} was sucesfully UPDATED in db", user.getId());
        return user.getId();
    }

    public int delete(Integer userID) {
        userRepository.deleteById(userID);
        LOGGER.debug("Sensor with id {} was sucesfully DELETED from the db", userID);
        return userID;
    }
}
