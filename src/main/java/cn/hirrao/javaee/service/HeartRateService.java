package cn.hirrao.javaee.service;

import cn.hirrao.javaee.entity.HeartRate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface HeartRateService {
    List<HeartRate> searchHeartRateById(Long uid);
    void insertHeartRate(long hrlId, long uid, float heartRate,String recordTime);
}
