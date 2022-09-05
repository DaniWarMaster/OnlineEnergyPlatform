package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.SensorsDTO;
import ro.tuc.ds2020.services.DeviceService;
import ro.tuc.ds2020.services.PersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/device")
public class DeviceController {
    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping()
    public ResponseEntity<List<DeviceDTO>> getDevices() {
        List<DeviceDTO> dtos = deviceService.findDevices();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Integer> insertProsumer(@Valid @RequestBody DeviceDTO deviceDTO) {
        int deviceID = deviceService.insert(deviceDTO);
        return new ResponseEntity<>(deviceID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DeviceDTO> getDevice(@PathVariable("id") Integer deviceID) {
        DeviceDTO dto = deviceService.findDeviceById(deviceID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Integer> deleteSensor(@PathVariable("id") Integer deviceID) {
        DeviceDTO deviceDTO = deviceService.findDeviceById(deviceID);
        //if(sensorsDTO.getDevice() != null) {
        //    deviceService.deleteByID(sensorsDTO.getDevice().getId());
        //}
        int deviceIDrez = deviceService.delete(deviceID);
        return new ResponseEntity<>(deviceIDrez, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Integer> updateSensor(@Valid @RequestBody DeviceDTO deviceDTO) {
        int sensorsID = deviceService.update(deviceDTO);
        return new ResponseEntity<>(sensorsID, HttpStatus.CREATED);
    }
}
