package cn.hirrao.javaee.service;

import cn.hirrao.javaee.entity.BloodPressure;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BloodPressureService {
    List<BloodPressure> getBloodPressureList(Long uid, String date);

    void insertBloodPressure(long bplId, long userId, String date, float sbp, float dbp);
}
