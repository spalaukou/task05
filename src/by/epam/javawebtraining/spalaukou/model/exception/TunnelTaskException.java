package by.epam.javawebtraining.spalaukou.model.exception;

/**
 * @author Stanislau Palaukou on 31.03.2019
 * @project task05
 */

public class TunnelTaskException extends Exception {
    public TunnelTaskException() {
        super();
    }

    public TunnelTaskException(String message) {
        super(message);
    }

    public TunnelTaskException(String message, Throwable cause) {
        super(message, cause);
    }

    public TunnelTaskException(Throwable cause) {
        super(cause);
    }

    protected TunnelTaskException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
