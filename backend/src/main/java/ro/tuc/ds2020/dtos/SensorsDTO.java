package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.Device;

import java.util.UUID;

public class SensorsDTO {
    private int id;
    private String description;
    private int maximumValue;
    private Device device;

    public SensorsDTO() {}

    public SensorsDTO(int id, String description, int maximumValue, Device device) {
        this.id = id;
        this.description = description;
        this.maximumValue = maximumValue;
        this.device = device;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(int maximumValue) {
        this.maximumValue = maximumValue;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
