package world.xuewei.dto;

import lombok.Data;

@Data
public class ChatMessage {
    private String from;      // 发送者
    private String to;        // 接收者
    private String content;   // 消息内容
    private String time;      // 时间戳
    private Integer doctorId; // 医生ID
    private Integer userId;   // 用户ID
}