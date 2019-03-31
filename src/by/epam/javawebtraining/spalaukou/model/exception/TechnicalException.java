package by.epam.javawebtraining.spalaukou.model.exception;

/**
 * @author Stanislau Palaukou on 31.03.2019
 * @project task05
 */

public class TechnicalException extends TunnelTaskException {
    public TechnicalException() {
        super();
    }

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public TechnicalException(Throwable cause) {
        super(cause);
    }

    protected TechnicalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
