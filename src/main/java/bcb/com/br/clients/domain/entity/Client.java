package bcb.com.br.clients.domain.entity;

import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity(name = "clients")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String telephone;
    @NotEmpty
    @CPF
    private String cpf;
    @NotEmpty
    @Size(min = 14, max = 14)
    @CNPJ
    @Column(unique = true)
    private String cnpj;
    @NotEmpty
    private String companyName;

}
