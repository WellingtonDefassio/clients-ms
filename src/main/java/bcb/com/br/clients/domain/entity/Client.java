package bcb.com.br.clients.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import java.util.UUID;



@Entity(name = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
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
    @OneToOne(mappedBy = "clientId")
    private Balance balance;

}
