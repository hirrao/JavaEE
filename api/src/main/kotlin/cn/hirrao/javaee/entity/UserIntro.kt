package cn.hirrao.javaee.entity

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.Setter

@Getter
@Setter
@AllArgsConstructor
class UserIntro {
    @TableId(value = "uid")
    private val uid: Long? = null

    @TableField("intro")
    private val intro: String? = null
}
