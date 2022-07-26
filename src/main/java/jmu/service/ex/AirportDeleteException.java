package jmu.service.ex;

public class AirportDeleteException extends ServiceException{
    public AirportDeleteException() {
        super();
    }

    public AirportDeleteException(String message) {
        super(message);
    }

    public AirportDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public AirportDeleteException(Throwable cause) {
        super(cause);
    }

    protected AirportDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
