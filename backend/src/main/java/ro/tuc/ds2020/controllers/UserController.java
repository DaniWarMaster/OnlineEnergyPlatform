package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.SensorsDTO;
import ro.tuc.ds2020.dtos.UserDTO;
import ro.tuc.ds2020.services.SensorService;
import ro.tuc.ds2020.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> dtos = userService.findUsers();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Integer> insertProsumer(@Valid @RequestBody UserDTO userDTO) {
        int userID = userService.insert(userDTO);
        return new ResponseEntity<>(userID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Integer userID) {
        UserDTO dto = userService.findUserById(userID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> getUserByNameAndPassword(@Valid @RequestBody UserDTO userDTO) {
        UserDTO dto = userService.findUserByNameAndPassword(userDTO.getName(), userDTO.getPassword());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Integer> deleteSensor(@PathVariable("id") Integer userID) {
        UserDTO userDTO = userService.findUserById(userID);
        //if(userDTO.getDevices() != null) {
        //    deviceService.deleteByID(sensorsDTO.getDevice().getId());
        //}
        int sensorsID = userService.delete(userID);
        return new ResponseEntity<>(sensorsID, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Integer> updateSensor(@Valid @RequestBody UserDTO userDTO) {
        int sensorsID = userService.update(userDTO);
        return new ResponseEntity<>(sensorsID, HttpStatus.CREATED);
    }

}
