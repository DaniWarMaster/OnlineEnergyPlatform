package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.entities.Device;

public class DeviceBuilder {
    public DeviceBuilder() {

    }

    public static DeviceDTO toDeviceDTO(Device device) {
        return new DeviceDTO(
                device.getId(),
                device.getDescription(),
                device.getAddress(),
                device.getMaximumEnergyConsumption(),
                device.getAverageEnergyConsumption(),
                device.getSensors(),
                device.getUser(),
                device.getRecords()
        );
    }

    public static Device toEntity(DeviceDTO deviceDTO) {
        return new Device(
                deviceDTO.getDescription(),
                deviceDTO.getAddress(),
                deviceDTO.getMaximumEnergyConsumption(),
                deviceDTO.getAverageEnergyConsumption()
        );
    }
}
