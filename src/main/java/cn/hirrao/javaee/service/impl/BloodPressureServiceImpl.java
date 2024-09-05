package cn.hirrao.javaee.service.impl;

import cn.hirrao.javaee.entity.BloodPressure;
import cn.hirrao.javaee.entity.BloodPressureTable;
import cn.hirrao.javaee.mapper.BloodPressureMapper;
import cn.hirrao.javaee.mapper.BloodPressureTableMapper;
import cn.hirrao.javaee.service.BloodPressureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BloodPressureServiceImpl implements BloodPressureService {
    private final BloodPressureMapper BloodPressureMapper;
    private final BloodPressureTableMapper BloodPressureTableMapper;

    @Autowired
    public BloodPressureServiceImpl(BloodPressureMapper bloodPressureMapper, BloodPressureTableMapper bloodPressureTableMapper) {
        BloodPressureMapper = bloodPressureMapper;
        BloodPressureTableMapper = bloodPressureTableMapper;
    }

    @Override
    public List<BloodPressure> getBloodPressureList(Long uid, LocalDate date) {
        return BloodPressureMapper.getBloodPressureList(uid, date);
    }

    @Override
    public void insertBloodPressure(long bplId, long userId, float sbp, float dbp, String recordTime, String classification, String riskLevel) {
        BloodPressureMapper.insertBloodPressure(bplId, userId, sbp, dbp, recordTime, classification, riskLevel);
    }

    @Override
    public BloodPressureTable getBloodPressureTable(LocalDate date, Long uid) {
        return BloodPressureTableMapper.getBloodPressureTable(date, uid);
    }
}
