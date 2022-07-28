package jmu.service.ex;

public class TicketTypeDuplicateException extends ServiceException{
    public TicketTypeDuplicateException() {
        super();
    }

    public TicketTypeDuplicateException(String message) {
        super(message);
    }

    public TicketTypeDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public TicketTypeDuplicateException(Throwable cause) {
        super(cause);
    }

    protected TicketTypeDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
