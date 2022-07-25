package jmu.service.ex;

public class PassengerDeleteException extends ServiceException{
    public PassengerDeleteException() {
        super();
    }

    public PassengerDeleteException(String message) {
        super(message);
    }

    public PassengerDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public PassengerDeleteException(Throwable cause) {
        super(cause);
    }

    protected PassengerDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
