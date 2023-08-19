package bcb.com.br.clients.controller.dto;

import bcb.com.br.clients.domain.entity.Client;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class ClientRequest {
    @NotEmpty
    private String name;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String telephone;
    @CPF
    @NotEmpty
    private String cpf;
    @CNPJ
    @NotEmpty
    private String cnpj;
    @NotEmpty
    private String companyName;
    
    public Client toModel() {
        return new Client(null, name, email, telephone, cpf, cnpj,companyName);
    }
}
