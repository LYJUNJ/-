package world.xuewei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import world.xuewei.dto.ChatMessage;

@RestController
public class ChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void handleChat(ChatMessage message) {
        String channel = "/topic/chat/" + message.getDoctorId() + "_" + message.getUserId();
        System.out.println("广播频道: " + channel); // 调试用
        messagingTemplate.convertAndSend(channel, message);
    }
}