package jmu.controllers;


import jmu.service.ex.*;
import jmu.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/** 控制器类的基类 */
public class BaseController {
    /** 操作成功的状态码 */
    public static final int OK = 200;
    /**
     * 请求处理方法，这个方法的返回值就是需要传递给前端的数据
     * @ExceptionHandler用于统一处理方法抛出的异常
     * */
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<Void>(e);
        if (e instanceof UserIDDuplicateException) {
            result.setState(4000);
            result.setMessage("用户已存在");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("注册时产生未知异常");
        }else if (e instanceof UserNotFoundException) {
            result.setState(5001);
            result.setMessage("用户数据不存在的异常");
        }else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("用户密码错误的异常");
        }
        return result;
    }

    /**
     * 获取session对象中的userID
     * @param session session对象
     * @return 当前用户登录的id
     */
    protected final String getUserIDFromSession(HttpSession session){
        return session.getAttribute("userID").toString();
    }
    /**
     * 获得当前用户的权限
     * @param session session对象
     * @return 当前用户的权限
     */
    protected final Integer getPermissionFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("permission").toString());
    }
}
