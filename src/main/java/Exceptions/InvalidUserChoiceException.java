package Exceptions;

public class InvalidUserChoiceException extends RuntimeException{
    public InvalidUserChoiceException() {
        super();
    }

    public InvalidUserChoiceException(String message) {
        super(message);
    }

    public InvalidUserChoiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserChoiceException(Throwable cause) {
        super(cause);
    }
}
