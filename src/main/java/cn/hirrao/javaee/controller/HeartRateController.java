package cn.hirrao.javaee.controller;

import cn.hirrao.javaee.entity.HeartRate;
import cn.hirrao.javaee.entity.Result;
import cn.hirrao.javaee.service.HeartRateService;
import cn.hirrao.javaee.utils.SnowFlake;
import cn.hirrao.javaee.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/heartRate")
public class HeartRateController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final SnowFlake snowFlake = new SnowFlake(1, 3);
    private final HeartRateService heartRateService;

    public HeartRateController(HeartRateService heartRateService){
        this.heartRateService=heartRateService;
    }

    @GetMapping("/searchHRById")
    public Result searchHRById(@RequestBody Map<String, String> map){
        logger.debug("通过id查找心率信息",map);
        Long userId=Long.parseLong(map.get("userId"));
        List<HeartRate> result = heartRateService.searchHeartRateById(userId);
        return Result.success(result);
    }

    @PostMapping("/insertHR")
    public Result insertHR(@RequestBody Map<String, String> map){
        logger.debug("插入心率信息",map);
        String userId=map.get("userId");
        String heartRate=map.get("heartRate");
        String date=map.get("date");
        if(StringUtil.isEmpty(userId)||StringUtil.isEmpty(heartRate)||StringUtil.isEmpty(date)){
            return Result.error(101, "错误输入");
        }
        heartRateService.insertHeartRate(snowFlake.nextId(),Long.parseLong(userId),Float.parseFloat(heartRate),date);
        return Result.success();
    }
}
