package cn.hirrao.javaee.entity

import com.baomidou.mybatisplus.annotation.TableId
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.Setter

@Getter
@Setter
@AllArgsConstructor
class HeartRate {
    @TableId(value = "hrlId")
    private val hrlId: Long? = null
    private val uid: Long? = null
    private val heartRate: Float? = null
    private val recordTime: String? = null
}
