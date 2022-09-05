package ro.tuc.ds2020.dtos;

import java.sql.Date;

public class RecordDTOBaseline {
    private int id;
    private int recordedValue;
    private int hour;
    //private Device device;

    public RecordDTOBaseline() {

    }

    public RecordDTOBaseline(int id, int recordedValue) {
        this.id = id;
        this.recordedValue = recordedValue;
        //this.device = device;
    }

    public RecordDTOBaseline(int id, int recordedValue, int hour) {
        this.id = id;
        this.recordedValue = recordedValue;
        this.hour = hour;
        //this.device = device;
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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
}
