package bcb.com.br.clients.service;

import bcb.com.br.clients.domain.entity.Balance;
import bcb.com.br.clients.domain.entity.Client;
import bcb.com.br.clients.domain.enums.CurrentType;
import bcb.com.br.clients.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {
    private final BalanceRepository balanceRepository;

    public Balance creatDefaultCampaign(Client client) {
        Balance defaultBalance = new Balance(null, 0d, 0d, 0.25d, CurrentType.PRE, client);
        return balanceRepository.save(defaultBalance);
    }
}
