package cn.hirrao.javaee.service.impl;

import cn.hirrao.javaee.entity.BloodPressure;
import cn.hirrao.javaee.mapper.BloodPressureMapper;
import cn.hirrao.javaee.service.BloodPressureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodPressureServiceImpl implements BloodPressureService {
    private final BloodPressureMapper BloodPressureMapper;

    @Autowired
    public BloodPressureServiceImpl(BloodPressureMapper bloodPressureMapper) {
        BloodPressureMapper = bloodPressureMapper;
    }

    @Override
    public List<BloodPressure> getBloodPressureList(Long uid, String date) {
        return BloodPressureMapper.getBloodPressureList(uid, date);
    }

    @Override
    public void insertBloodPressure(long bplId, long userId, String date, float sbp, float dbp, String classification, String riskLevel) {
        BloodPressureMapper.insertBloodPressure(bplId, userId, date, sbp, dbp, classification, riskLevel);
    }
}
