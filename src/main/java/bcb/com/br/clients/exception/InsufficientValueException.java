package bcb.com.br.clients.exception;

public class InsufficientValueException extends RuntimeException {
    public InsufficientValueException() {
        super("account dont have sufficient balance");
    }
}
