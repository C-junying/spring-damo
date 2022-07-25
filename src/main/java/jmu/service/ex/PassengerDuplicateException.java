package jmu.service.ex;

public class PassengerDuplicateException extends ServiceException{
    public PassengerDuplicateException() {
        super();
    }

    public PassengerDuplicateException(String message) {
        super(message);
    }

    public PassengerDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public PassengerDuplicateException(Throwable cause) {
        super(cause);
    }

    protected PassengerDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
