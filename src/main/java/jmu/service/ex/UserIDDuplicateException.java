package jmu.service.ex;


/** 用户名重复的异常 */
public class UserIDDuplicateException extends ServiceException{
    public UserIDDuplicateException() {
        super();
    }

    public UserIDDuplicateException(String message) {
        super(message);
    }

    public UserIDDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIDDuplicateException(Throwable cause) {
        super(cause);
    }

    protected UserIDDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
