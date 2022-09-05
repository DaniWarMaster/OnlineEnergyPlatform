package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.Device;

import java.sql.Date;

public class RecordDTO {
    private int id;
    private int recordedValue;
    private Date date;
    private Device device;

    public RecordDTO() {

    }

    public RecordDTO(int id, int recordedValue, Device device) {
        this.id = id;
        this.recordedValue = recordedValue;
        this.device = device;
    }

    public RecordDTO(int id, int recordedValue, Date date, Device device) {
        this.id = id;
        this.recordedValue = recordedValue;
        this.date = date;
        this.device = device;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecordedValue() {
        return recordedValue;
    }

    public void setRecordedValue(int recordedValue) {
        this.recordedValue = recordedValue;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
