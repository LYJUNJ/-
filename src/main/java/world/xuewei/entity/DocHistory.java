package world.xuewei.entity;

import lombok.Data;
import java.util.Date;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocHistory {
    private Long id;
    private Integer userId;
    private String userName;
    private Integer doctorId;
    private String doctorName;
    private Date appointTime;
    private Integer status; // 0=待问诊，1=已问诊
    private Date createTime;
    private Date updateTime;
    private String medicalRecord;
}