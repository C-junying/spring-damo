package jmu.service.ex;

public class AirportIDDuplicateException extends ServiceException{
    public AirportIDDuplicateException() {
        super();
    }

    public AirportIDDuplicateException(String message) {
        super(message);
    }

    public AirportIDDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public AirportIDDuplicateException(Throwable cause) {
        super(cause);
    }

    protected AirportIDDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
