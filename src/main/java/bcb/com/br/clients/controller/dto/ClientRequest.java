package bcb.com.br.clients.controller.dto;

import bcb.com.br.clients.domain.entity.Client;
import lombok.Data;

@Data
public class ClientRequest {

    private String name;

    private String email;

    private String telephone;

    private String cpf;

    private String cnpj;

    private String companyName;


    public Client toModel() {
        return new Client(null, name, email, telephone, cpf, cnpj,companyName);
    }
}
