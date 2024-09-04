package cn.hirrao.javaee.controller;

import cn.hirrao.javaee.entity.Result;
import cn.hirrao.javaee.service.BloodPressureService;
import cn.hirrao.javaee.utils.SnowFlake;
import cn.hirrao.javaee.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bp")
public class BloodPressureController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final SnowFlake snowFlake = new SnowFlake(1, 2);
    private final BloodPressureService bloodPressureService;

    public BloodPressureController(BloodPressureService bloodPressureService) {
        this.bloodPressureService = bloodPressureService;
    }

    @GetMapping("/record/uid")
    public Result getUid(@RequestBody Map<String, String> map) {
        logger.debug("/record/uid接受请求{}", map);
        var result = bloodPressureService.getBloodPressureList(Long.parseLong(map.get("userId")), map.get("date"));
        return Result.success(result);
    }

    @PostMapping("/record/insert")
    public Result insert(@RequestBody Map<String, String> map) {
        logger.debug("/record/insert接受请求{}", map);
        var userId = map.get("userId");
        var date = map.get("date");
        var sbp = map.get("sbp");
        var dbp = map.get("dbp");
        if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(date) || StringUtil.isEmpty(sbp) || StringUtil.isEmpty(dbp)) {
            return Result.error(101, "参数错误");
        }

        //将string处理为数值进行处理
        float sbpValue = Float.parseFloat(sbp);
        float dbpValue = Float.parseFloat(dbp);

        // 生成 classification 和 riskLevel
        String classification = generateClassification(sbpValue, dbpValue);
        String riskLevel = generateRiskLevel(sbpValue, dbpValue);

        bloodPressureService.insertBloodPressure(snowFlake.nextId(), Long.parseLong(userId), date, sbpValue, dbpValue, classification, riskLevel);
        return Result.success();
    }

    private String generateClassification(float sbp, float dbp) {
        if (sbp >= 180 || dbp >= 110) {
            return "3级高血压";
        } else if (sbp >= 160 || dbp >= 100) {
            return "2级高血压";
        } else if (sbp >= 140 || dbp >= 90) {
            return "1级高血压";
        } else {
            return "正常";
        }
    }

    private String generateRiskLevel(float sbp, float dbp) {
        if (sbp >= 180 || dbp >= 110) {
            return "重度";
        } else if (sbp >= 160 || dbp >= 100) {
            return "中度";
        } else if (sbp >= 140 || dbp >= 90) {
            return "轻度";
        } else if (sbp >= 120 || dbp >= 80) {
            return "正常高值";
        } else if ((sbp <= 90 && dbp <= 60) || (dbp <= 60 && sbp < 140)) {
            return "偏低";
        } else {
            return "正常";
        }
    }
}
