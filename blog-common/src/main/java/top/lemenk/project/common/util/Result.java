package top.lemenk.project.common.util;

/**
 * @Description 返回数据json格式类
 * @Author lemenk@163.com
 * @Created Date: 2020/11/27 17:18
 * @ClassName Result
 * @Remark 解决返回状态码，结果集，说明文字
 */
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Integer getCode() {
        return code;
    }

    public Result<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Result() {
    }

    public Result(String message) {
        this.message = message;
    }

    public Result(Integer code, String message) {
        this(code,message,null);
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
