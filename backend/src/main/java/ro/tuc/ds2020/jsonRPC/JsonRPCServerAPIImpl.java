package ro.tuc.ds2020.jsonRPC;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.RecordController;
import ro.tuc.ds2020.dtos.RecordDTO;
import ro.tuc.ds2020.dtos.RecordDTOBaseline;
import ro.tuc.ds2020.dtos.RecordDTOTransfer;

import java.util.ArrayList;
import java.util.List;

@Service
@AutoJsonRpcServiceImpl
public class JsonRPCServerAPIImpl implements JsonRPCServerAPI {

    @Autowired
    RecordController recordController;

    @Override
    public String printRecord(String record) {
        System.out.println("Am intrat aici: " + record);
        return "Am fost la Server : " + record;
    }

    @Override
    public List<RecordDTOTransfer> getRecords(Integer id, Integer days) {
        List<RecordDTOTransfer> rezult = new ArrayList<RecordDTOTransfer>();

        rezult = recordController.getRecordsByDevice(id, days).getBody();

        return rezult;
    }

    @Override
    public List<RecordDTOTransfer> getRecordsByProgram(Integer id, Integer hours) {
        List<RecordDTOTransfer> rezult = new ArrayList<RecordDTOTransfer>();

        rezult = recordController.getRecordsByProgram(id, hours).getBody();

        return rezult;
    }

    @Override
    public Double getBaseline(Integer id) {
        Double rezult = 0.0;

        rezult = recordController.getBaseline(id).getBody();

        return rezult;
    }

    @Override
    public List<RecordDTOBaseline> getBaselineGraph(Integer id) {
        List<RecordDTOBaseline> rezult = new ArrayList<RecordDTOBaseline>();

        rezult = recordController.getBaselineGraph(id).getBody();

        return rezult;
    }
}
