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
            result.setMessage("插入时产生未知异常");
        }else if (e instanceof UserNotFoundException) {
            result.setState(5001);
            result.setMessage("用户数据不存在的异常");
        }else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("用户密码错误的异常");
        }else if (e instanceof UserUpdateException) {
            result.setState(5003);
            result.setMessage("更新用户数据产生未知异常");
        }else if (e instanceof PassengerDuplicateException) {
            result.setState(5004);
            result.setMessage("用户乘机人重复异常");
        }else if (e instanceof PassengerNotFoundException) {
            result.setState(5005);
            result.setMessage("乘机人不存在异常");
        }else if (e instanceof PassengerDeleteException) {
            result.setState(5006);
            result.setMessage("删除乘机人时产生未知异常");
        }else if (e instanceof PayException) {
            result.setState(5007);
            result.setMessage("支付异常");
        }else if (e instanceof CityNotFoundException) {
            result.setState(5008);
            result.setMessage("城市不存在异常");
        }else if (e instanceof CityDeleteException) {
            result.setState(5009);
            result.setMessage("删除城市时产生未知异常");
        }else if (e instanceof CityUpdateException) {
            result.setState(5009);
            result.setMessage("更新城市时产生未知异常");
        }else if (e instanceof AirportNotFoundException) {
            result.setState(5010);
            result.setMessage("机场不存在异常");
        }else if (e instanceof AirportDeleteException) {
            result.setState(5011);
            result.setMessage("删除机场时产生未知异常");
        }else if (e instanceof AirportUpdateException) {
            result.setState(5012);
            result.setMessage("更新机场时产生未知异常");
        }else if (e instanceof UserDeleteException) {
            result.setState(5013);
            result.setMessage("删除用户时产生未知异常");
        }else if (e instanceof CityIDDuplicateException) {
            result.setState(5014);
            result.setMessage("城市已存在");
        }else if (e instanceof AirportIDDuplicateException) {
            result.setState(5015);
            result.setMessage("机场已存在");
        }else if (e instanceof TerminalDuplicateException) {
            result.setState(5016);
            result.setMessage("航站楼已存在");
        }else if (e instanceof RemainingException) {
            result.setState(5017);
            result.setMessage("航班剩余票不足或小于您的订购");
        }else if (e instanceof BalanceException) {
            result.setState(5018);
            result.setMessage("尊敬的顾客您好，您的账户余额不足，请充值后，再订票");
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
