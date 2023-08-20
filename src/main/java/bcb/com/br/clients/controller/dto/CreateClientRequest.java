package bcb.com.br.clients.controller.dto;


import bcb.com.br.clients.domain.entity.Client;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;



@Data
public class CreateClientRequest {
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
    @Size(min = 14, max = 14)
    private String cnpj;
    @NotEmpty
    private String companyName;

    public Client toModel() {
        return new Client(null, name, email, telephone, cpf, cnpj,companyName, null);
    }
}
