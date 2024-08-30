package cn.hirrao.javaee.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor  //生成无参构造
@AllArgsConstructor //生成有参构造
@Getter
public class Result {
    private Integer code;//状态码，0表示成功，-1表示失败
    private String message;//提示信息
    private Object data;//响应数据，比如token

    public static  Result success(Object data) {
        return new Result(0,"success",data);
    }
    public static Result success() {
        return new Result(0,"success",null);
    }
    public static Result error(String message) {
        return new Result(-1,message,null);
    }
}
