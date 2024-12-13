package exceptions;

public class InvalidResearchPaperException extends Exception {
	public InvalidResearchPaperException(String errorMessage) {
    	super(errorMessage);
    }
}
