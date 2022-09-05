import com.googlecode.jsonrpc4j.spring.rest.JsonRpcRestClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class JSONRPCClient {
    private static final Logger logger = Logger.getLogger(JSONRPCClient.class.getName());

    @RequestMapping(value = "printperson")
    @ResponseBody
    public Object printRecord() {
        try {
            URL url = new URL(getAPIUrl());
            JsonRpcRestClient client = new JsonRpcRestClient(url);
            Map<String, Object> params = new HashMap<>();
            params.put("record", "Praise The SUN");
            String result = client.invoke("printRecord", params, String.class);
            return result;
        } catch (Throwable t) {
            logger.log(Level.SEVERE, "Error while invoking printRecord API", t);
        }
        return null;
    }

    @RequestMapping(value = "getBaseline")
    @ResponseBody
    public Object getBaseline() {
        try {
            URL url = new URL(getAPIUrl());
            JsonRpcRestClient client = new JsonRpcRestClient(url);
            Map<String, Object> params = new HashMap<>();
            params.put("clientID", 1);
            Double result = client.invoke("getBaseline", params, Double.class);
            return result;
        } catch (Throwable t) {
            logger.log(Level.SEVERE, "Error while invoking getBaseline API", t);
        }
        return null;
    }

    @RequestMapping(value = "getBaselineGraph")
    @ResponseBody
    public Object getBaselineGraph() {
        try {
            URL url = new URL(getAPIUrl());
            JsonRpcRestClient client = new JsonRpcRestClient(url);
            Map<String, Object> params = new HashMap<>();
            params.put("clientID", 1);
            RecordDTOBaseline[] result = client.invoke("getBaselineGraph", params, RecordDTOBaseline[].class);
            return result;
        } catch (Throwable t) {
            logger.log(Level.SEVERE, "Error while invoking getBaseline API", t);
        }
        return null;
    }

    //facem primul punct aici cel cu zilele
    @RequestMapping(value = "getRecords")
    @ResponseBody
    public Object getRecords(int chartDays) {
        try {
            URL url = new URL(getAPIUrl());
            JsonRpcRestClient client = new JsonRpcRestClient(url);
            Map<String, Object> params = new HashMap<>();
            params.put("clientID", 1);
            params.put("days",chartDays);
            RecordDTO[] result =  client.invoke("getRecords", params, RecordDTO[].class);

            String showStuff = "";
            for(RecordDTO aux : result) {
                showStuff += aux.getId() + " ";
            }
            //System.out.println(result);
            System.out.println("Stuff: " + showStuff);

            return result;
        } catch (Throwable t) {
            logger.log(Level.SEVERE, "Error while invoking printRecord API", t);
        }
        return null;
    }

    //facem primul punct aici cel cu programul
    @RequestMapping(value = "getRecords")
    @ResponseBody
    public Object getRecordsByProgram(int hours) {
        try {
            URL url = new URL(getAPIUrl());
            JsonRpcRestClient client = new JsonRpcRestClient(url);
            Map<String, Object> params = new HashMap<>();
            params.put("clientID", 1);
            params.put("hours",hours);
            RecordDTO[] result =  client.invoke("getRecordsByProgram", params, RecordDTO[].class);

            String showStuff = "";
            for(RecordDTO aux : result) {
                showStuff += aux.getId() + " ";
            }
            //System.out.println(result);
            System.out.println("Stuff: " + showStuff);

            return result;
        } catch (Throwable t) {
            logger.log(Level.SEVERE, "Error while invoking getRecordsByProgram API", t);
        }
        return null;
    }

    private static String getAPIUrl() {
        //return "https://theoneandonlyapp.herokuapp.com/api";
        return "http://localhost:8080/api";
    }
}
