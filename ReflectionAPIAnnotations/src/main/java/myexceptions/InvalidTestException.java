package myexceptions;

public class InvalidTestException extends RuntimeException{
    public InvalidTestException(String message) {
        super(message);
    }
}
