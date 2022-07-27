package jmu.service.ex;

public class CityIDDuplicateException extends ServiceException{
    public CityIDDuplicateException() {
        super();
    }

    public CityIDDuplicateException(String message) {
        super(message);
    }

    public CityIDDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CityIDDuplicateException(Throwable cause) {
        super(cause);
    }

    protected CityIDDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
