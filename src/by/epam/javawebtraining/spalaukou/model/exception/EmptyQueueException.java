package by.epam.javawebtraining.spalaukou.model.exception;

/**
 * @author Stanislau Palaukou on 31.03.2019
 * @project task05
 */

public class EmptyQueueException extends TechnicalException {
    public EmptyQueueException() {
        super();
    }

    public EmptyQueueException(String message) {
        super(message);
    }

    public EmptyQueueException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyQueueException(Throwable cause) {
        super(cause);
    }

    protected EmptyQueueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
