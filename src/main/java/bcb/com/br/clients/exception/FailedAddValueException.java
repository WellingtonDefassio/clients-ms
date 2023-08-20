package bcb.com.br.clients.exception;

public class FailedAddValueException extends RuntimeException {
    public FailedAddValueException() {
        super("cant add value");
    }
}
