package jmu.service.ex;

public class OnFlightDeleteException extends ServiceException{
    public OnFlightDeleteException() {
        super();
    }

    public OnFlightDeleteException(String message) {
        super(message);
    }

    public OnFlightDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public OnFlightDeleteException(Throwable cause) {
        super(cause);
    }

    protected OnFlightDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
