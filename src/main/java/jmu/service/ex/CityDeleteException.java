package jmu.service.ex;

public class CityDeleteException extends ServiceException{
    public CityDeleteException() {
        super();
    }

    public CityDeleteException(String message) {
        super(message);
    }

    public CityDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public CityDeleteException(Throwable cause) {
        super(cause);
    }

    protected CityDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
