package cn.hirrao.javaee.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HeartRate {
    @TableId(value = "hrlId")
    private Long hrlId;
    private Long uid;
    private Float heartRate;
    private String recordTime;
}
