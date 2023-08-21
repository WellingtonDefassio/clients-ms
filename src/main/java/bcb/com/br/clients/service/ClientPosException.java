package bcb.com.br.clients.service;

public class ClientPosException extends RuntimeException {
    public ClientPosException() {
        super("client must pay off the actual expense first");
    }
}
