package app.exceptions;

public class ApplicationAlreadyExistsException extends Exception {
    public ApplicationAlreadyExistsException() {
        super("Application already exists. Cannot apply twice.");
    }
}
