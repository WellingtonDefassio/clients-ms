package bcb.com.br.clients.controller.dto;

import bcb.com.br.clients.domain.entity.Balance;
import bcb.com.br.clients.domain.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateClientResponse {

    private UUID id;

    private String name;

    private String email;

    private String telephone;

    private String cpf;

    private String cnpj;

    private String companyName;

    private CreateBalanceResponse balance;

    public CreateClientResponse fromModel(Client client, Balance balance) {
        return CreateClientResponse.builder()
                .id(client.getId())
                .email(client.getEmail())
                .telephone(client.getTelephone())
                .cpf(client.getCpf())
                .cnpj(client.getCnpj())
                .companyName(client.getCompanyName())
                .balance(new CreateBalanceResponse().fromModel(balance))
                .build();
    }
}
