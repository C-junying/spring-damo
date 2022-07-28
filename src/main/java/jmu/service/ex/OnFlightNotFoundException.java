package jmu.service.ex;

public class OnFlightNotFoundException extends ServiceException{
    public OnFlightNotFoundException() {
        super();
    }

    public OnFlightNotFoundException(String message) {
        super(message);
    }

    public OnFlightNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OnFlightNotFoundException(Throwable cause) {
        super(cause);
    }

    protected OnFlightNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
