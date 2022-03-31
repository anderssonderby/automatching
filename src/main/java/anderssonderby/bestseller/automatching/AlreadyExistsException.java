package anderssonderby.bestseller.automatching;

public class AlreadyExistsException extends Exception {

    public AlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
