package top.lemenk.project.common.exception;

import top.lemenk.project.common.constant.CodeConstant;

/**
 * @Description 异常处理工具类
 * @Author lemenk@163.com
 * @Created Date: 2020/12/18 14:44
 * @ClassName FinancialException
 * @Remark
 */
public class CommonException extends RuntimeException {
    private static final long serialVersionUID = -5417123878943287180L;
    private final Integer code;
    private final String message;

    /**
     *
     */
    public CommonException() {
        super(String.format("%d - %s", CodeConstant.ERROR_CODE, CodeConstant.ERROR_MESSAGE));
        this.code = CodeConstant.ERROR_CODE;
        this.message = CodeConstant.ERROR_MESSAGE;
    }

    public CommonException(String message) {
        super(String.format("%d - %s", CodeConstant.ERROR_CODE, message));
        this.code = CodeConstant.ERROR_CODE;
        this.message = message;
    }

    /**
     *
     * @param code 异常码
     * @param message 异常信息
     */
    public CommonException(Integer code, String message) {
        super(String.format("%d - %s", code, message));
        this.code = code;
        this.message = message;
    }

    /**
     *
     * @param code 异常码
     * @param message 异常信息
     * @param cause 异常堆栈
     */
    public CommonException(Integer code, String message, Throwable cause) {
        super(String.format("%d - %s", code, message), cause);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
