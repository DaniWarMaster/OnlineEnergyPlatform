package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.Device;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class UserDTO {
    private int id;
    private boolean isAdmin;
    private String name;
    private String address;
    private String birthDate;
    private String password;
    private List<Device> devices;

    public UserDTO() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDTO(int id, boolean isAdmin, String name, String address, String birthDate, String password, List<Device> devices) {
        this.id = id;
        this.isAdmin = isAdmin;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.password = password;
        this.devices = devices;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
