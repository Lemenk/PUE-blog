package top.lemenk.project.common.util;

import org.apache.commons.lang3.StringUtils;
import top.lemenk.project.common.constant.CodeConstant;

/**
 * @Description TODO
 * @author lemenk@163.com
 * @date 2020/12/4 14:18
 * @className ResultWrapperUtil
 * @Remark
 */
public class ResultWrapperUtil {
    private ResultWrapperUtil() {
    }

    public static <E> Result<E> wrap(int code, String message, E o) {
        return new Result<>(code, message, o);
    }

    public static <E> Result<E> wrap(String message, E o) {
        return new Result<>(CodeConstant.SUCCESS_CODE, message, o);
    }

    public static <E> Result<E> wrap(E o) {
        return new Result<>(CodeConstant.SUCCESS_CODE, CodeConstant.SUCCESS_MESSAGE, o);
    }

    public static <E> Result<E> ok() {
        return new Result<>(CodeConstant.SUCCESS_CODE, CodeConstant.SUCCESS_MESSAGE);
    }

    public static <E> Result<E> ok(E o) {
        return new Result<>(CodeConstant.SUCCESS_CODE, CodeConstant.SUCCESS_MESSAGE, o);
    }

    public static <E> Result<E> ok(String message) {
        return new Result<>(CodeConstant.SUCCESS_CODE, StringUtils.isBlank(message) ? CodeConstant.SUCCESS_MESSAGE : message);
    }

    public static <E> Result<E> ok(String message, E o) {
        return new Result<>(CodeConstant.SUCCESS_CODE, message, o);
    }

    public static <E> Result<E> error() {
        return new Result<>(CodeConstant.ERROR_CODE, CodeConstant.ERROR_MESSAGE);
    }

    public static <E> Result<E> error(String message) {
        return new Result<>(CodeConstant.ERROR_CODE, StringUtils.isBlank(message) ? CodeConstant.ERROR_MESSAGE : message);
    }

    public static <E> Result<E> error(E o) {
        return new Result<>(CodeConstant.ERROR_CODE, CodeConstant.ERROR_MESSAGE, o);
    }

    public static <E> Result<E> error(String message, E o) {
        return new Result<>(CodeConstant.ERROR_CODE, message, o);
    }

    public static <E> Result<E> illegalArgument() {
        return new Result<>(CodeConstant.ILLEGAL_ARGUMENT_CODE, CodeConstant.ILLEGAL_ARGUMENT_MESSAGE);
    }

    public static <E> Result<E> illegalArgument(String message) {
        return new Result<>(CodeConstant.ILLEGAL_ARGUMENT_CODE, message);
    }

    public static <E> Result<E> noPermission() {
        return new Result<>(CodeConstant.NO_PERMISSION_MESSAGE);
    }

    public static <E> Result<E> noPermission(String message) {
        return new Result<E>(CodeConstant.NO_PERMISSION_CODE, message);
    }
}
