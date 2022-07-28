package jmu.service.ex;

public class RemainingException extends ServiceException{
    public RemainingException() {
        super();
    }

    public RemainingException(String message) {
        super(message);
    }

    public RemainingException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemainingException(Throwable cause) {
        super(cause);
    }

    protected RemainingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
