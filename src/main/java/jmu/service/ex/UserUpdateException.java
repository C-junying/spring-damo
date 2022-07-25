package jmu.service.ex;

/**用户在更新数据时产生的未知的异常*/
public class UserUpdateException extends ServiceException{
    public UserUpdateException() {
        super();
    }

    public UserUpdateException(String message) {
        super(message);
    }

    public UserUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserUpdateException(Throwable cause) {
        super(cause);
    }

    protected UserUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
