package world.xuewei.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("doctor")
public class Doctor {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer departmentId;
    private String title;
    private String avatar;
    private String intro;
    private BigDecimal price;
    private Date createTime;
    private Date updateTime;
    private String departmentName;

    // 非数据库字段，需要时手动设置
    @TableField(exist = false)
    private Department department;

    /**
     * 安全获取科室ID（永远只返回departmentId字段）
     */
    public Integer getDepartmentId() {
        return this.departmentId;
    }

    /**
     * 设置关联科室对象（可选操作）
     */
    public void setDepartment(Department department) {
        this.department = department;
        // 可选：保持departmentId和departmentName同步
        if (department != null) {
            this.departmentId = department.getId();
            this.departmentName = department.getName();
        }
    }
}