package cn.hirrao.javaee.entity

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor

@Getter
@AllArgsConstructor
@NoArgsConstructor
class BloodPressureTable {
    private val minSBP: Float? = null
    private val maxSBP: Float? = null
    private val avgSBP: Float? = null
    private val minDBP: Float? = null
    private val maxDBP: Float? = null
    private val avgDBP: Float? = null
    private val minPP: Float? = null
    private val maxPP: Float? = null
    private val avgPP: Float? = null
}
