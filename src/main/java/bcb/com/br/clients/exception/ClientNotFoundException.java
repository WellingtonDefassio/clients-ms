package bcb.com.br.clients.exception;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException() {
        super("cnpj not found");
    }
}
