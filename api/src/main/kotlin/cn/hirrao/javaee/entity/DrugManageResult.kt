package cn.hirrao.javaee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class DrugManageResult {
    private Long drugId;
    private String drugName;
    private String frequency;
    private String unit;
    private Float dosage;
    private int isActive;

    private Long alertId;
    private LocalTime alertTime;
    private LocalDate eatTime;
}
