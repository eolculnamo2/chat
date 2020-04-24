package com.challenge.chatapp.resources;

import com.challenge.chatapp.dto.Message;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

  @MessageMapping("/send-msg")
  @SendTo("/topic/emit-msg")
  public Message greeting(Message message) throws Exception {
    Message msg = new Message(message.getUsername(), message.getMessage());

    if (message.getAttachment() != null) {
      System.out.println(message.getAttachment());
    }

    return msg;
  }

}