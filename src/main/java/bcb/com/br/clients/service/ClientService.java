package bcb.com.br.clients.service;

import bcb.com.br.clients.controller.dto.*;
import bcb.com.br.clients.domain.entity.Balance;
import bcb.com.br.clients.domain.entity.Client;
import bcb.com.br.clients.domain.enums.CurrentType;
import bcb.com.br.clients.exception.*;
import bcb.com.br.clients.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    public GetClientResponse changeLimit(ChangeLimitRequest request) {
        Client client = clientRepository.getClientByCnpj(request.getCnpj()).orElseThrow(ClientNotFoundException::new);
        if (request.getLimit() > client.getBalance().getCurrentExpense() && client.getBalance().getCurrentType().equals(CurrentType.POS)) {
            client.getBalance().setValue(request.getLimit());
            return new GetClientResponse().fromModel(clientRepository.save(client));
        } else {
            throw new InvalidNewLimitException();
        }
    }

    public GetClientResponse changeType(ChangeTypeRequest request) {
        Client client = clientRepository.getClientByCnpj(request.getCnpj()).orElseThrow(ClientNotFoundException::new);
        if (client.getBalance().getCurrentType().equals(CurrentType.PRE)) {
            return changeToPos(client);
        } else {
            return changeToPre(client);
        }
    }

    private GetClientResponse changeToPos(Client client) {
        if (Objects.equals(client.getBalance().getValue(), client.getBalance().getCurrentExpense())) {
            client.getBalance().setValue(0d);
            client.getBalance().setCurrentType(CurrentType.POS);
            return new GetClientResponse().fromModel(clientRepository.save(client));
        } else {
            throw new ClientPreException();
        }
    }

    private GetClientResponse changeToPre(Client client) {
        if (client.getBalance().getCurrentExpense() == 0.0) {
            client.getBalance().setValue(0d);
            client.getBalance().setCurrentType(CurrentType.PRE);
            return new GetClientResponse().fromModel(clientRepository.save(client));
        } else {
            throw new ClientPosException();
        }
    }
}
