package com.hirrao.javaee.service.impl;

import com.hirrao.javaee.entity.BloodPressure;
import com.hirrao.javaee.entity.BloodPressureTable;
import com.hirrao.javaee.mapper.BloodPressureMapper;
import com.hirrao.javaee.mapper.BloodPressureTableMapper;
import com.hirrao.javaee.service.BloodPressureService;
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

    @Override
    public List<String> getRiskLevel(Long uid, LocalDate date) {
        return BloodPressureMapper.getRiskLevel(uid, date);
    }
}
