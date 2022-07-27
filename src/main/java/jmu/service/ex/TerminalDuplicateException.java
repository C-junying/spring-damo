package jmu.service.ex;

public class TerminalDuplicateException extends ServiceException{
    public TerminalDuplicateException() {
        super();
    }

    public TerminalDuplicateException(String message) {
        super(message);
    }

    public TerminalDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public TerminalDuplicateException(Throwable cause) {
        super(cause);
    }

    protected TerminalDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
