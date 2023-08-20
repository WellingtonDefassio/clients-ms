package bcb.com.br.clients.controller.dto;

import bcb.com.br.clients.domain.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetClientResponse {

    private Long id;

    private String name;

    private String email;

    private String telephone;

    private String cpf;

    private String cnpj;

    private String companyName;

    private CreateBalanceResponse balance;

    public GetClientResponse fromModel(Client client) {
        return GetClientResponse.builder()
                .id(client.getId())
                .email(client.getEmail())
                .telephone(client.getTelephone())
                .cpf(client.getCpf())
                .cnpj(client.getCnpj())
                .companyName(client.getCompanyName())
                .balance(new CreateBalanceResponse().fromModel(client.getBalance()))
                .build();
    }
}
