package cn.hirrao.javaee.entity


data class Result(
    val code: Int? = null, //状态码，0表示成功，-1表示失败
    val message: String? = null,//提示信息
    val data: Any? = null, //响应数据，比如token
)

fun success(data: Any?): Result {
    return Result(0, "success", data)
}


fun success(): Result {
    return Result(0, "success", null)
}


fun error(code: Int, message: String?): Result {
    return Result(code, message, null)
}


