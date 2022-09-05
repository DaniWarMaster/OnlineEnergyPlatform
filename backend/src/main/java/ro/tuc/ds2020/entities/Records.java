package ro.tuc.ds2020.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Records {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column
    private int recordedValue;

    @Column
    private Date timestamp;

    @JsonBackReference
    @ManyToOne
    private Device device;

    public Records() {

    }

    public Records(int recordedValue, Device device) {
        this.recordedValue = recordedValue;
        this.device = device;
    }

    public Records(int recordedValue, Date timestamp, Device device) {
        this.recordedValue = recordedValue;
        this.timestamp = timestamp;
        this.device = device;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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
}
