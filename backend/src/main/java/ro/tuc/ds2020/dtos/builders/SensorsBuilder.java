package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.SensorsDTO;
import ro.tuc.ds2020.entities.Sensors;

public class SensorsBuilder {
    public SensorsBuilder() {}

    public static SensorsDTO toSensorsDTO(Sensors sensors) {
        return new SensorsDTO(
                sensors.getId(),
                sensors.getDescription(),
                sensors.getMaximumValue(),
                sensors.getDevice()
        );
    }

    public static Sensors toEntity(SensorsDTO sensorsDTO) {
        return new Sensors(
                sensorsDTO.getDescription(),
                sensorsDTO.getMaximumValue()
        );
    }
}
