package cn.hirrao.javaee.controller;

import cn.hirrao.javaee.entity.Result;
import cn.hirrao.javaee.service.BloodPressureService;
import cn.hirrao.javaee.utils.SnowFlake;
import cn.hirrao.javaee.utils.StringUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bp")
public class BloodPressureController {
    private final SnowFlake snowFlake = new SnowFlake(1, 2);
    private final BloodPressureService bloodPressureService;

    public BloodPressureController(BloodPressureService bloodPressureService) {
        this.bloodPressureService = bloodPressureService;
    }

    @GetMapping("/record/uid")
    public Result getUid(@RequestBody Map<String, String> map) {
        var result = bloodPressureService.getBloodPressureList(Long.parseLong(map.get("userId")), map.get("date"));
        return Result.success(result);
    }

    @PostMapping("/record/insert")
    public Result insert(@RequestBody Map<String, String> map) {
        var userId = map.get("userId");
        var date = map.get("date");
        var sbp = map.get("sbp");
        var dbp = map.get("dbp");
        if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(date) || StringUtil.isEmpty(sbp) || StringUtil.isEmpty(dbp)) {
            return Result.error(101, "参数错误");
        }
        bloodPressureService.insertBloodPressure(snowFlake.nextId(), Long.parseLong(userId), date, Float.parseFloat(sbp), Float.parseFloat(dbp));
        return Result.success();
    }
}
