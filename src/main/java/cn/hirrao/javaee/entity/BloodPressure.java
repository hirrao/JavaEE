package cn.hirrao.javaee.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BloodPressure {
    @TableId(value = "bplld")
    private Long bplld;
    private Long uid;
    private Float SBP;
    private Float DBP;
    private String recordTime;
}
