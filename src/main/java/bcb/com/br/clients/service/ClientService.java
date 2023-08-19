package bcb.com.br.clients.service;

import bcb.com.br.clients.controller.dto.ClientRequest;
import bcb.com.br.clients.controller.dto.ClientResponse;
import bcb.com.br.clients.domain.entity.Client;
import bcb.com.br.clients.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientResponse createClient(ClientRequest clientRequest) {
        Client client = clientRequest.toModel();
        clientRepository.save(client);
        return new ClientResponse().fromModel(client);
    }

}
