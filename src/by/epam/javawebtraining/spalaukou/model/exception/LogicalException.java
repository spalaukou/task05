package by.epam.javawebtraining.spalaukou.model.exception;

/**
 * @author Stanislau Palaukou on 31.03.2019
 * @project task05
 */

public class LogicalException extends TunnelTaskException {
    public LogicalException() {
        super();
    }

    public LogicalException(String message) {
        super(message);
    }

    public LogicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogicalException(Throwable cause) {
        super(cause);
    }

    protected LogicalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
