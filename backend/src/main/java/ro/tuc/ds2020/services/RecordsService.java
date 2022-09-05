package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.RecordDTO;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.dtos.builders.RecordsBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Records;
import ro.tuc.ds2020.repositories.DeviceRepository;
import ro.tuc.ds2020.repositories.RecordsRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecordsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecordsService.class);
    private final RecordsRepository recordsRepository;

    @Autowired
    public RecordsService(RecordsRepository recordsRepository) {this.recordsRepository = recordsRepository;}

    public List<RecordDTO> findRecords() {
        List<Records> recordList = recordsRepository.findAll();
        return recordList.stream()
                .map(RecordsBuilder::toRecordDTO)
                .collect(Collectors.toList());
    }

    public RecordDTO findRecordById(Integer id) {
        Optional<Records> prosumerOptional = recordsRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Record with id {} was not found in db", id);
            throw new ResourceNotFoundException(Records.class.getSimpleName() + " with id: " + id);
        }
        return RecordsBuilder.toRecordDTO(prosumerOptional.get());
    }

    public Integer insert(RecordDTO recordsDTO) {
        Records records = RecordsBuilder.toEntity(recordsDTO);
        records = recordsRepository.save(records);
        LOGGER.debug("Record with id {} was inserted in db", records.getId());
        return records.getId();
    }
}
