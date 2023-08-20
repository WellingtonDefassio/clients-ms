package bcb.com.br.clients.service;

import bcb.com.br.clients.controller.dto.CreateClientRequest;
import bcb.com.br.clients.controller.dto.CreateClientResponse;
import bcb.com.br.clients.controller.dto.GetClientResponse;
import bcb.com.br.clients.controller.dto.ReceiveEntries;
import bcb.com.br.clients.domain.entity.Balance;
import bcb.com.br.clients.domain.entity.Client;
import bcb.com.br.clients.domain.enums.CurrentType;
import bcb.com.br.clients.exception.ClientNotFoundException;
import bcb.com.br.clients.exception.FailedAddValueException;
import bcb.com.br.clients.exception.InsufficientValueException;
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

    public GetClientResponse addValue(ReceiveEntries receiveEntries) {
        Client client = clientRepository.getClientByCnpj(receiveEntries.getCnpj()).orElseThrow(ClientNotFoundException::new);
        if (client.getBalance().getCurrentType().toString().equalsIgnoreCase(CurrentType.PRE.toString())) {
            addPreCredit(receiveEntries, client);
            return new GetClientResponse().fromModel(client);
        }
        if (client.getBalance().getCurrentType().toString().equalsIgnoreCase(CurrentType.POS.toString())) {
            freePosLimit(receiveEntries, client);
            return new GetClientResponse().fromModel(client);
        }
        //todo melhorar logica de erro
        throw new FailedAddValueException();
    }

    private void freePosLimit(ReceiveEntries receiveEntries, Client client) {
        if (client.getBalance().getCurrentExpense() < receiveEntries.getValue()) {
            client.getBalance().setCurrentExpense(client.getBalance().getCurrentExpense() - receiveEntries.getValue());
            clientRepository.save(client);
        }
    }

    private void addPreCredit(ReceiveEntries receiveEntries, Client client) {
        client.getBalance().setValue(client.getBalance().getValue() + receiveEntries.getValue());
        clientRepository.save(client);
    }

    public GetClientResponse chargeMessage(ReceiveEntries receiveEntries) {
        Client client = clientRepository.getClientByCnpj(receiveEntries.getCnpj()).orElseThrow(ClientNotFoundException::new);
        double balance = client.getBalance().getValue() - client.getBalance().getCurrentExpense();
        if (balance >= client.getBalance().getCurrentRate()) {
            client.getBalance().setCurrentExpense(client.getBalance().getCurrentExpense() + client.getBalance().getCurrentRate());
            Client save = clientRepository.save(client);
            return new GetClientResponse().fromModel(save);
        } else {
            throw new InsufficientValueException();
        }
    }
}
