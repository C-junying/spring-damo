package jmu.service.ex;

public class AirportNotFoundException extends ServiceException{
    public AirportNotFoundException() {
        super();
    }

    public AirportNotFoundException(String message) {
        super(message);
    }

    public AirportNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AirportNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AirportNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
