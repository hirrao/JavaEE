package com.hirrao.javaee.service.impl;

import com.hirrao.javaee.entity.HeartRate;
import com.hirrao.javaee.mapper.HeartRateMapper;
import com.hirrao.javaee.service.HeartRateService;
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
