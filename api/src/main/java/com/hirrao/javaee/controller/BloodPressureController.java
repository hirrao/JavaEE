package com.hirrao.javaee.controller;

import com.hirrao.javaee.entity.Result;
import com.hirrao.javaee.entity.User;
import com.hirrao.javaee.service.BloodPressureService;
import com.hirrao.javaee.utils.SnowFlake;
import com.hirrao.javaee.utils.StringUtil;
import com.hirrao.javaee.utils.ThreadLocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

import static com.hirrao.javaee.utils.BloodPressureUtil.generateClassification;
import static com.hirrao.javaee.utils.BloodPressureUtil.generateRiskLevel;

@RestController
@RequestMapping("/bp")
public class BloodPressureController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final SnowFlake snowFlake = new SnowFlake(1, 2);
    private final BloodPressureService bloodPressureService;

    public BloodPressureController(BloodPressureService bloodPressureService) {
        this.bloodPressureService = bloodPressureService;
    }

    @PostMapping("/record/uid")
    public Result getUid(@RequestBody Map<String, String> map) {
        logger.debug("/record/uid接受请求{}", map);
        User user = ThreadLocalUtil.get();
        LocalDate date = LocalDate.parse(map.get("date"));
        var result = bloodPressureService.getBloodPressureList(user.getUid(), date);
        return Result.success(result);
    }

    @PostMapping("/record/table")
    public Result getTable(@RequestBody Map<String, String> map) {
        logger.debug("/record/table接受请求{}", map);
        User user = ThreadLocalUtil.get();
        LocalDate date = LocalDate.parse(map.get("date"));
        var result = bloodPressureService.getBloodPressureTable(date, user.getUid());
        return Result.success(result);
    }

    @PostMapping("/record/riskLevel")
    public Result getRiskLevel(@RequestBody Map<String, String> map) {
        logger.debug("/record/riskLevel接受请求{}", map);
        User user = ThreadLocalUtil.get();
        LocalDate date = LocalDate.parse(map.get("date"));
        var result = bloodPressureService.getRiskLevel(user.getUid(), date);
        return Result.success(result);
    }

    @PostMapping("/record/insert")
    public Result insert(@RequestBody Map<String, String> map) {
        logger.debug("/record/insert接受请求{}", map);
        User user = ThreadLocalUtil.get();
        var userId = user.getUid();
        var recordTime = map.get("date");
        var sbp = map.get("sbp");
        var dbp = map.get("dbp");
        if (StringUtil.isEmpty(recordTime) || StringUtil.isEmpty(sbp) || StringUtil.isEmpty(dbp)) {
            return Result.error(101, "参数错误");
        }

        //将string处理为数值进行处理
        float sbpValue = Float.parseFloat(sbp);
        float dbpValue = Float.parseFloat(dbp);

        // 生成 classification 和 riskLevel
        String classification = generateClassification(sbpValue, dbpValue);
        String riskLevel = generateRiskLevel(sbpValue, dbpValue);

        bloodPressureService.insertBloodPressure(snowFlake.nextId(), userId, sbpValue, dbpValue, recordTime, classification, riskLevel);
        return Result.success();
    }
}
