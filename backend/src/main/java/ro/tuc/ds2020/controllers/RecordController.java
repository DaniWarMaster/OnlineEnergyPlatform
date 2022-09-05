package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.RecordDTO;
import ro.tuc.ds2020.dtos.RecordDTOBaseline;
import ro.tuc.ds2020.dtos.RecordDTOTransfer;
import ro.tuc.ds2020.dtos.builders.RecordsBuilder;
import ro.tuc.ds2020.services.DeviceService;
import ro.tuc.ds2020.services.RecordsService;

import javax.validation.Valid;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/record")
public class RecordController {
    private final RecordsService recordsService;

    @Autowired
    public RecordController(RecordsService recordsService) {
        this.recordsService = recordsService;
    }

    @GetMapping()
    public ResponseEntity<List<RecordDTO>> getRecords() {
        List<RecordDTO> dtos = recordsService.findRecords();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/recordsByDevice")
    public ResponseEntity<List<RecordDTOTransfer>> getRecordsByDevice(@PathVariable("id") Integer personId, @PathVariable("days") Integer days) {
        List<RecordDTO> dtos = recordsService.findRecords();

        LocalDate date = LocalDate.now().minusDays(days);
        Date dateBefore = Date.valueOf(date);

        List<RecordDTOTransfer> dtosResult = new ArrayList<RecordDTOTransfer>();
        for(RecordDTO aux: dtos) {
            if(aux.getDevice().getId() == personId && aux.getDate() != null && aux.getDate().after(dateBefore)) {
                dtosResult.add(RecordsBuilder.recordDTOTransfer(aux));
            }
        }

        return new ResponseEntity<>(dtosResult, HttpStatus.OK);
    }

    @GetMapping("/getRecordsByProgram")
    public ResponseEntity<List<RecordDTOTransfer>> getRecordsByProgram(@PathVariable("id") Integer personId, @PathVariable("hours") Integer hours) {
        List<RecordDTO> dtos = recordsService.findRecords();

        LocalDate date = LocalDate.now().minusDays(hours);
        Date dateBefore = Date.valueOf(date);

        List<RecordDTOTransfer> dtosResult = new ArrayList<RecordDTOTransfer>();
        for(RecordDTO aux: dtos) {
            if(aux.getDevice().getId() == personId && aux.getDate() != null && aux.getDate().after(dateBefore)) {
                dtosResult.add(RecordsBuilder.recordDTOTransfer(aux));
            }
        }

        return new ResponseEntity<>(dtosResult, HttpStatus.OK);
    }

    @GetMapping("/baseline")
    public ResponseEntity<Double> getBaseline(@PathVariable("id") Integer personId) {
        List<RecordDTO> dtos = recordsService.findRecords();

        LocalDate date = LocalDate.now().minusDays(7);
        Date dateBefore7Days = Date.valueOf(date);

        /// modificam ceva pe aici
        Double dtosResult = 0.0;
        int nrOfDays = 0;
        for(RecordDTO aux : dtos) {
            if(aux.getDevice().getId() == personId && aux.getDate() != null && aux.getDate().after(dateBefore7Days)) {
                System.out.println(aux.getId() + " " + aux.getDate() + " " + aux.getRecordedValue());
                dtosResult += aux.getRecordedValue();
                nrOfDays++;
            }
        }

        //dtosResult = dtosResult / nrOfDays;

        dtosResult = dtosResult / 7;

        return new ResponseEntity<>(dtosResult, HttpStatus.OK);
    }

    @GetMapping("/baselineGraph")
    public ResponseEntity<List<RecordDTOBaseline>> getBaselineGraph(@PathVariable("id") Integer personId) {
        List<RecordDTO> dtos = recordsService.findRecords();

        LocalDate date = LocalDate.now().minusDays(7);
        Date dateBefore7Days = Date.valueOf(date);

        /// modificam ceva pe aici
        List<RecordDTOBaseline> dtosResult = new ArrayList<RecordDTOBaseline>();
        int nrOfDays = 0;
        int hourInitial = 7;
        for(RecordDTO aux : dtos) {
            if(aux.getDevice().getId() == personId && aux.getDate() != null && aux.getDate().after(dateBefore7Days)) {
                System.out.println(aux.getId() + " " + aux.getDate() + " " + aux.getRecordedValue());
                //dtosResult += aux.getRecordedValue();
                //nrOfDays++;
                dtosResult.add(new RecordDTOBaseline(aux.getId(),aux.getRecordedValue()/7, hourInitial));
                hourInitial++;
            }
        }

        //dtosResult = dtosResult / nrOfDays;


        return new ResponseEntity<>(dtosResult, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Integer> insertProsumer(@Valid @RequestBody RecordDTO recordDTO) {
        Date date = recordDTO.getDate();

        Date gmt = new Date(date.getTime() - Calendar.getInstance().getTimeZone().getOffset(date.getTime()));

        recordDTO.setDate(gmt);

        Integer recordID = recordsService.insert(recordDTO);
        return new ResponseEntity<>(recordID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RecordDTO> getRecord(@PathVariable("id") Integer personId) {


        RecordDTO dto = recordsService.findRecordById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
