package bcb.com.br.clients.service;

import bcb.com.br.clients.controller.dto.CreateClientRequest;
import bcb.com.br.clients.controller.dto.CreateClientResponse;
import bcb.com.br.clients.controller.dto.GetClientResponse;
import bcb.com.br.clients.domain.entity.Balance;
import bcb.com.br.clients.domain.entity.Client;
import bcb.com.br.clients.exception.ClientNotFoundException;
import bcb.com.br.clients.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final BalanceService balanceService;

    public CreateClientResponse createClient(CreateClientRequest createClientRequest) {
        Client client = createClientRequest.toModel();
        clientRepository.save(client);
        Balance balance = balanceService.creatDefaultCampaign(client);
        return new CreateClientResponse().fromModel(client, balance);
    }

    public GetClientResponse getClient(String cnpj) {
        Client client = clientRepository.getClientByCnpj(cnpj).orElseThrow(ClientNotFoundException::new);
        return new GetClientResponse().fromModel(client);
    }
}
