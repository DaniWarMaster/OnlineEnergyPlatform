package ro.tuc.ds2020.jsonRPC;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import ro.tuc.ds2020.dtos.RecordDTO;
import ro.tuc.ds2020.dtos.RecordDTOBaseline;
import ro.tuc.ds2020.dtos.RecordDTOTransfer;

import java.util.List;

@JsonRpcService("/api")
public interface JsonRPCServerAPI {

    String printRecord(@JsonRpcParam(value = "record") String record);

    List<RecordDTOTransfer> getRecords(@JsonRpcParam(value = "clientID") Integer id, @JsonRpcParam(value = "days") Integer days);

    List<RecordDTOTransfer> getRecordsByProgram(@JsonRpcParam(value = "clientID") Integer id, @JsonRpcParam(value = "hours") Integer hours);

    Double getBaseline(@JsonRpcParam(value = "clientID") Integer id);

    List<RecordDTOBaseline> getBaselineGraph(@JsonRpcParam(value = "clientID") Integer id);

}
