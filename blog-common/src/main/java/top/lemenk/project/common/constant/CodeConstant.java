package top.lemenk.project.common.constant;

import java.io.Serializable;

/**
 * @Description 操作code和message的常量类
 * @Author lemenk@163.com
 * @Created Date: 2020/12/4 10:53
 * @ClassName CodeConstant
 * @Remark
 */
public class CodeConstant implements Serializable {
    public static final Integer SUCCESS_CODE = 1;
    public static final Integer ERROR_CODE = 0;
    public static final Integer ILLEGAL_ARGUMENT_CODE = 100;
    public static final Integer NO_PERMISSION_CODE = 104;

    public static final String SUCCESS_MESSAGE = "操作成功";
    public static final String ILLEGAL_ARGUMENT_MESSAGE = "参数非法";
    public static final String ERROR_MESSAGE = "操作失败";
    public static final String SAVE_SUCCESS_MESSAGE = "新增成功";
    public static final String SAVE_ERROR_MESSAGE = "新增失败";
    public static final String MODIFY_SUCCESS_MESSAGE = "修改成功";
    public static final String MODIFY_ERROR_MESSAGE = "修改失败";
    public static final String DELETE_SUCCESS_MESSAGE = "删除成功";
    public static final String DELETE_ERROR_MESSAGE = "删除失败";
    public static final String NO_PERMISSION_MESSAGE = "无权限";

}
