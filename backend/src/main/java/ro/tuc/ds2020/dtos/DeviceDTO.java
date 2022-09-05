package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Records;
import ro.tuc.ds2020.entities.Sensors;
import ro.tuc.ds2020.entities.Users;

import java.util.List;
import java.util.UUID;

public class DeviceDTO {
    private int id;
    private String description;
    private String address;
    private int maximumEnergyConsumption;
    private int averageEnergyConsumption;
    private Sensors sensors;
    private Users user;
    private List<Records> records;

    public DeviceDTO() {

    }

    public DeviceDTO(int id, String description, String address, int maximumEnergyConsumption, int averageEnergyConsumption, Sensors sensors, Users user, List<Records> records) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.maximumEnergyConsumption = maximumEnergyConsumption;
        this.averageEnergyConsumption = averageEnergyConsumption;
        this.sensors = sensors;
        this.user = user;
        this.records = records;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMaximumEnergyConsumption() {
        return maximumEnergyConsumption;
    }

    public void setMaximumEnergyConsumption(int maximumEnergyConsumption) {
        this.maximumEnergyConsumption = maximumEnergyConsumption;
    }

    public int getAverageEnergyConsumption() {
        return averageEnergyConsumption;
    }

    public void setAverageEnergyConsumption(int averageEnergyConsumption) {
        this.averageEnergyConsumption = averageEnergyConsumption;
    }

    public Sensors getSensors() {
        return sensors;
    }

    public void setSensors(Sensors sensors) {
        this.sensors = sensors;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Records> getRecords() {
        return records;
    }

    public void setRecords(List<Records> records) {
        this.records = records;
    }
}
