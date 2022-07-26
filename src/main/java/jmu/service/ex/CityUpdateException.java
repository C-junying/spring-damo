package jmu.service.ex;

public class CityUpdateException extends ServiceException{
    public CityUpdateException() {
        super();
    }

    public CityUpdateException(String message) {
        super(message);
    }

    public CityUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CityUpdateException(Throwable cause) {
        super(cause);
    }

    protected CityUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
