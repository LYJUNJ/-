package world.xuewei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.xuewei.dao.DocHistoryDao;
import world.xuewei.entity.DocHistory;
import world.xuewei.service.DocHistoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

@Service
public class DocHistoryServiceImpl extends ServiceImpl<DocHistoryDao, DocHistory> implements DocHistoryService {
    @Autowired
    private DocHistoryDao docHistoryDao;

    @Override
    public List<DocHistory> findByDoctorId(Integer doctorId) {
        return docHistoryDao.selectByDoctorId(doctorId);
    }

    @Override
    public DocHistory get(Long id) {
        return docHistoryDao.selectById(id);
    }

    @Override
    public List<DocHistory> getByDoctorId(Long doctorId) {
        return docHistoryDao.selectByDoctorId(doctorId.intValue());
    }

    @Override
    public List<DocHistory> selectByDoctorId(Long doctorId) {
        return docHistoryDao.selectByDoctorId(doctorId.intValue());
    }

    @Override
    public List<DocHistory> query(DocHistory condition) {
        QueryWrapper<DocHistory> wrapper = new QueryWrapper<>();
        if (condition.getDoctorId() != null) {
            wrapper.eq("doctor_id", condition.getDoctorId());
        }
        if (condition.getUserId() != null) {
            wrapper.eq("user_id", condition.getUserId());
        }
        if (condition.getStatus() != null) {
            wrapper.eq("status", condition.getStatus());
        }
        return docHistoryDao.selectList(wrapper);
    }

    @Override
    public int countByUserIdAndDoctorIdAndStatus(Integer userId, Integer doctorId, Integer status) {
        return docHistoryDao.countByUserIdAndDoctorIdAndStatus(userId, doctorId, status);
    }

    @Override
    public List<DocHistory> findByUserId(Integer userId) {
        return docHistoryDao.findByUserId(userId);
    }
} 