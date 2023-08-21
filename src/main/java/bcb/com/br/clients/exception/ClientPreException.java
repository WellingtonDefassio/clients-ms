package bcb.com.br.clients.exception;

public class ClientPreException extends RuntimeException {

    public ClientPreException() {
        super("client must use remaining credit first.");
    }
}
