package exceptions;

public class InvalidSupervisorException extends Exception {
    public InvalidSupervisorException(String errorMessage) {
    	super(errorMessage);
    }
}
