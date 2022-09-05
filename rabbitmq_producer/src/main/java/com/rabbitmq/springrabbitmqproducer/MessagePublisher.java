package com.rabbitmq.springrabbitmqproducer;

import com.opencsv.CSVReader;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

@RestController
@EnableScheduling
public class MessagePublisher {

    @Autowired
    private RabbitTemplate template;

    //@PostMapping("/publish")
    @Scheduled(fixedDelay = 1000)
    public String publishMessage() {
        CustomMessage message = new CustomMessage();
        message.setMessageID(ConfigFile.id);


        message.setMessageDate(new Date());
        CSVReader reader = null;
        try
        {
            //parsing a CSV file into CSVReader class constructor
            reader = new CSVReader(new FileReader("sensor.csv"));
            String [] nextLine;
            //reads one line at a time
            while ((nextLine = reader.readNext()) != null)
            {
                for(String token : nextLine)
                {
                    System.out.print(token);

                    double value = Double.parseDouble(token);
                    message.setMessage(value);
                    message.setMessageDate(new Date());

                    template.convertAndSend(
                            MQConfig.EXCHANGE,
                            MQConfig.ROUTING_KEY, message);

                }
                System.out.print("\n");
                Thread.sleep(1000);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return "File Published";
    }

    public String publishSensorData() {
        CSVReader reader = null;
        CustomMessage message = new CustomMessage();
        message.setMessageID(ConfigFile.id);

        try
        {
            //parsing a CSV file into CSVReader class constructor
            reader = new CSVReader(new FileReader("sensor.csv"));
            String [] nextLine;
            //reads one line at a time
            while ((nextLine = reader.readNext()) != null)
            {
                for(String token : nextLine)
                {
                    System.out.print(token);

                    double value = Double.parseDouble(token);
                    message.setMessage(value);
                    message.setMessageDate(new Date());

                    template.convertAndSend(
                            MQConfig.EXCHANGE,
                            MQConfig.ROUTING_KEY, message);

                }
                System.out.print("\n");
                Thread.sleep(1000);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return "File Published";
    }
}
