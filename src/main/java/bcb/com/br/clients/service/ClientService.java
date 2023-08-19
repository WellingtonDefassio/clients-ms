package bcb.com.br.clients.service;

import bcb.com.br.clients.controller.dto.CreateClientRequest;
import bcb.com.br.clients.controller.dto.CreateClientResponse;
import bcb.com.br.clients.domain.entity.Client;
import bcb.com.br.clients.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public CreateClientResponse createClient(CreateClientRequest createClientRequest) {
        Client client = createClientRequest.toModel();
        clientRepository.save(client);
        return new CreateClientResponse().fromModel(client);
    }

}
