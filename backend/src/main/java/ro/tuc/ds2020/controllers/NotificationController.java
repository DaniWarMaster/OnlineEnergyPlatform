package ro.tuc.ds2020.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String showNotification(String excededValue) {
        return "BOOM MERGE -- ExcededValue" + excededValue;
    }
}
