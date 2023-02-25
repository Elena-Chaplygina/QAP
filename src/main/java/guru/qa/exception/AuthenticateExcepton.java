package guru.qa.exception;

public class AuthenticateExcepton extends Exception {
    public AuthenticateExcepton() {
    }

    public AuthenticateExcepton(String message) {
        super(message);
    }

    public AuthenticateExcepton(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticateExcepton(Throwable cause) {
        super(cause);
    }

    public AuthenticateExcepton(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
