package jmu.service.ex;

public class AirportUpdateException extends ServiceException{
    public AirportUpdateException() {
        super();
    }

    public AirportUpdateException(String message) {
        super(message);
    }

    public AirportUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public AirportUpdateException(Throwable cause) {
        super(cause);
    }

    protected AirportUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
