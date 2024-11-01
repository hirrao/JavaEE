package cn.hirrao.javaee.service;

import cn.hirrao.javaee.entity.BloodPressure;
import cn.hirrao.javaee.entity.BloodPressureTable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface BloodPressureService {
    List<BloodPressure> getBloodPressureList(Long uid, LocalDate date);

    void insertBloodPressure(long bplId, long userId, float sbp, float dbp, String recordTime, String classification, String riskLevel);

    BloodPressureTable getBloodPressureTable(LocalDate date, Long uid);

    List<String> getRiskLevel(Long uid, LocalDate date);
}
