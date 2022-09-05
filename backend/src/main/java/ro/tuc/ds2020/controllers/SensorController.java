package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.SensorsDTO;
import ro.tuc.ds2020.entities.Sensors;
import ro.tuc.ds2020.services.DeviceService;
import ro.tuc.ds2020.services.SensorService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin
@RequestMapping(value = "/sensor")
public class SensorController {
    private final SensorService sensorsService;
    private final DeviceService deviceService;

    @Autowired
    public SensorController(SensorService sensorsService, DeviceService deviceService) {
        this.sensorsService = sensorsService;
        this.deviceService = deviceService;
    }

    @GetMapping()
    public ResponseEntity<List<SensorsDTO>> getSensors() {
        List<SensorsDTO> dtos = sensorsService.findSensors();

        System.out.println(dtos);

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/empty")
    public ResponseEntity<List<SensorsDTO>> getEmptySensors() {
        List<SensorsDTO> dtos = sensorsService.findEmptySensors();

        System.out.println(dtos);

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Integer> insertProsumer(@Valid @RequestBody SensorsDTO sensorsDTO) {
        int sensorsID = sensorsService.insert(sensorsDTO);
        return new ResponseEntity<>(sensorsID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SensorsDTO> getSensor(@PathVariable("id") Integer sensorID) {
        SensorsDTO dto = sensorsService.findSensorsById(sensorID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Integer> deleteSensor(@PathVariable("id") Integer sensorID) {
        SensorsDTO sensorsDTO = sensorsService.findSensorsById(sensorID);
        if(sensorsDTO.getDevice() != null) {
            deviceService.delete(sensorsDTO.getDevice().getId());
        }
        int sensorsID = sensorsService.delete(sensorID);
        return new ResponseEntity<>(sensorsID, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Integer> updateSensor(@Valid @RequestBody SensorsDTO sensorsDTO) {
        int sensorsID = sensorsService.update(sensorsDTO);
        return new ResponseEntity<>(sensorsID, HttpStatus.CREATED);
    }

}
