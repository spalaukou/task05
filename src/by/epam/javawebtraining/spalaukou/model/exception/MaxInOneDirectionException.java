package by.epam.javawebtraining.spalaukou.model.exception;

/**
 * @author Stanislau Palaukou on 31.03.2019
 * @project task05
 */

public class MaxInOneDirectionException extends LogicalException {
    public MaxInOneDirectionException() {
        super();
    }

    public MaxInOneDirectionException(String message) {
        super(message);
    }

    public MaxInOneDirectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public MaxInOneDirectionException(Throwable cause) {
        super(cause);
    }

    protected MaxInOneDirectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
