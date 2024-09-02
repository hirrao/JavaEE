package cn.hirrao.javaee.service.impl;

import cn.hirrao.javaee.entity.HeartRate;
import cn.hirrao.javaee.mapper.HeartRateMapper;
import cn.hirrao.javaee.service.HeartRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeartRateServiceImpl implements HeartRateService {
    private final HeartRateMapper heartRateMapper;

    @Autowired
    public HeartRateServiceImpl(HeartRateMapper heartRateMapper){
        this.heartRateMapper=heartRateMapper;
    }

    @Override
    public List<HeartRate> searchHeartRateById(Long uid){
        return heartRateMapper.searchHeartRateById(uid);
    }
    @Override
    public void insertHeartRate(long hrlId, long uid, float heartRate,String recordTime){
        heartRateMapper.insertHeartRate(hrlId,uid,heartRate,recordTime);
    }
}
