package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.SensorsDTO;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.dtos.builders.SensorsBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Sensors;
import ro.tuc.ds2020.repositories.DeviceRepository;
import ro.tuc.ds2020.repositories.SensorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SensorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SensorService.class);
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {this.sensorRepository = sensorRepository;}

    public List<SensorsDTO> findSensors() {
        System.out.println("SensorRepository ALL RESULT: " + sensorRepository.findAll());

        List<Sensors> personList = sensorRepository.findAll();



        return personList.stream()
                .map(SensorsBuilder::toSensorsDTO)
                .collect(Collectors.toList());
    }

    public List<SensorsDTO> findEmptySensors() {
        //System.out.println("SensorRepository ALL RESULT: " + sensorRepository.findAll());

        List<Sensors> personList = sensorRepository.findAll();

        //System.out.println("LIST SHANI" + personList.stream().filter(item -> item.getDevice() == null).collect(Collectors.toList()));


        return (personList.stream().filter(item -> item.getDevice() == null).collect(Collectors.toList())).stream()
                .map(SensorsBuilder::toSensorsDTO)
                .collect(Collectors.toList());
    }

    public SensorsDTO findSensorsById(Integer id) {
        Optional<Sensors> prosumerOptional = sensorRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Sensors with id {} was not found in db", id);
            throw new ResourceNotFoundException(Sensors.class.getSimpleName() + " with id: " + id);
        }
        return SensorsBuilder.toSensorsDTO(prosumerOptional.get());
    }

    public int insert(SensorsDTO sensorsDTO) {
        Sensors sensors = SensorsBuilder.toEntity(sensorsDTO);
        sensors = sensorRepository.save(sensors);
        LOGGER.debug("Sensor with id {} was INSERTED in db", sensors.getId());
        return sensors.getId();
    }

    public int update(SensorsDTO sensorsDTO) {
        Sensors sensors = SensorsBuilder.toEntity(sensorsDTO);
        sensors.setId(sensorsDTO.getId());
        sensors.setDevice(sensorsDTO.getDevice());

        sensorRepository.save(sensors);
        LOGGER.debug("Sensor with id {} was sucesfully UPDATED in db", sensors.getId());
        return sensors.getId();
    }

    public int delete(Integer sensorsDTO) {
        sensorRepository.deleteById(sensorsDTO);
        LOGGER.debug("Sensor with id {} was sucesfully DELETED from the db", sensorsDTO);
        return sensorsDTO;
    }
}
