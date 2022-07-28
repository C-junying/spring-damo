package jmu.service.ex;

public class OnFlightDuplicateException extends ServiceException{
    public OnFlightDuplicateException() {
        super();
    }

    public OnFlightDuplicateException(String message) {
        super(message);
    }

    public OnFlightDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public OnFlightDuplicateException(Throwable cause) {
        super(cause);
    }

    protected OnFlightDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
