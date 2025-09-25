package world.xuewei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName("department")
public class Department {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String info;
    private Date createTime;
    private Date updateTime;
}