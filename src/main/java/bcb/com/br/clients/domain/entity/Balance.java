package bcb.com.br.clients.domain.entity;

import bcb.com.br.clients.domain.enums.CurrentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client clientId;

}
