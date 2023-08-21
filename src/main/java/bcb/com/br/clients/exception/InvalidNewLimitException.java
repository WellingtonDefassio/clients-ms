package bcb.com.br.clients.exception;

public class InvalidNewLimitException extends RuntimeException {

    public InvalidNewLimitException() {
        super("new limit must be less then actual expense and account must be POS.");
    }
}
