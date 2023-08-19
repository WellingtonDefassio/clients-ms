package bcb.com.br.clients.domain.entity;

import bcb.com.br.clients.domain.enums.CurrentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "balances")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double value;
    private Double currentExpense;
    private Double currentRate;
    @Enumerated(EnumType.STRING)
    private CurrentType currentType;
    @OneToOne(mappedBy = "balance")
    private Client clientId;

}
