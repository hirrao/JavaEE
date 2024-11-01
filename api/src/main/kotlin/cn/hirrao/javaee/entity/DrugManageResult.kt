package cn.hirrao.javaee.entity

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.Setter
import java.time.LocalDate
import java.time.LocalTime

@Getter
@Setter
@AllArgsConstructor
class DrugManageResult {
    private val drugId: Long? = null
    private val drugName: String? = null
    private val frequency: String? = null
    private val unit: String? = null
    private val dosage: Float? = null
    private val isActive = 0

    private val alertId: Long? = null
    private val alertTime: LocalTime? = null
    private val eatTime: LocalDate? = null
}
