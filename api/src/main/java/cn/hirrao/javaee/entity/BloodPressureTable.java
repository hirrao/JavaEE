package cn.hirrao.javaee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BloodPressureTable {
    private Float minSBP;
    private Float maxSBP;
    private Float avgSBP;
    private Float minDBP;
    private Float maxDBP;
    private Float avgDBP;
    private Float minPP;
    private Float maxPP;
    private Float avgPP;
}
