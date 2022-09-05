package ro.tuc.ds2020.dtos;

import java.sql.Date;

public class RecordDTOTransfer {
    private int id;
    private int recordedValue;
    private Date date;
    //private Device device;

    public RecordDTOTransfer() {

    }

    public RecordDTOTransfer(int id, int recordedValue) {
        this.id = id;
        this.recordedValue = recordedValue;
        //this.device = device;
    }

    public RecordDTOTransfer(int id, int recordedValue, Date date) {
        this.id = id;
        this.recordedValue = recordedValue;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
