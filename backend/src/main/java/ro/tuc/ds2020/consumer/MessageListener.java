package ro.tuc.ds2020.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import ro.tuc.ds2020.controllers.DeviceController;
import ro.tuc.ds2020.controllers.RecordController;
import ro.tuc.ds2020.controllers.SensorController;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.RecordDTO;
import ro.tuc.ds2020.dtos.SensorsDTO;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.entities.Device;

import java.sql.Date;

@Component
public class MessageListener {

    @Autowired
    SensorController sensorController;

    @Autowired
    RecordController recordController;

    @Autowired
    DeviceController deviceController;

    @Autowired
    private SimpMessagingTemplate webSocket;

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(CustomMessage message) {
        //System.out.println(message);

        double value = message.getMessage();
        int sensor_id = message.getMessageID();
        System.out.println("Sensor " + sensor_id + " --> " + value);

        ResponseEntity<SensorsDTO> rezult = sensorController.getSensor(sensor_id);
        ResponseEntity<DeviceDTO> rezult2 = deviceController.getDevice(5);
        SensorsDTO sensorsDTO = rezult.getBody();

        recordController.insertProsumer(new RecordDTO(0, (int)value, (Date) message.getMessageDate(), DeviceBuilder.toEntity(rezult2.getBody())));

        System.out.println("Sensor " + sensorsDTO.getId() + " maximum_value: " + sensorsDTO.getMaximumValue());

        if(value > sensorsDTO.getMaximumValue()) {
            webSocket.convertAndSend("/topic/greetings",""+value);
        }
    }
}
