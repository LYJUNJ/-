package world.xuewei.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import world.xuewei.entity.DocHistory;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

@Repository
public interface DocHistoryDao extends BaseMapper<DocHistory> {
    @Select("SELECT * FROM doc_history WHERE doctor_id = #{doctorId}")
    List<DocHistory> selectByDoctorId(Integer doctorId);

    @Select("SELECT COUNT(1) FROM doc_history WHERE user_id = #{userId} AND doctor_id = #{doctorId} AND status = #{status}")
    int countByUserIdAndDoctorIdAndStatus(@Param("userId") Integer userId, @Param("doctorId") Integer doctorId, @Param("status") Integer status);

    @Select("SELECT * FROM doc_history WHERE user_id = #{userId}")
    List<DocHistory> findByUserId(@Param("userId") Integer userId);
}