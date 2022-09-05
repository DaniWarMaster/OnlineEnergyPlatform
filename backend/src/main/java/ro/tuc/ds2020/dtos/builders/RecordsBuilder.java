package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.RecordDTO;
import ro.tuc.ds2020.dtos.RecordDTOTransfer;
import ro.tuc.ds2020.entities.Records;

public class RecordsBuilder {

    private RecordsBuilder() {

    }

    public static RecordDTO toRecordDTO(Records records) {
        return new RecordDTO(records.getId(), records.getRecordedValue(), records.getTimestamp(), records.getDevice());
    }

    public static Records toEntity(RecordDTO recordDTO) {
        return new Records(recordDTO.getRecordedValue(),recordDTO.getDate(), recordDTO.getDevice());
    }

    public static RecordDTOTransfer recordDTOTransfer(RecordDTO recordDTO) {
        return new RecordDTOTransfer(recordDTO.getId(), recordDTO.getRecordedValue(), recordDTO.getDate());
    }
}
