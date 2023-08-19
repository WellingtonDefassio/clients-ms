package bcb.com.br.clients.controller.dto;

import bcb.com.br.clients.domain.entity.Balance;
import bcb.com.br.clients.domain.enums.CurrentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBalanceResponse {
    private Double value;
    private Double currentExpense;
    private Double currentRate;
    private CurrentType currentType;

    public CreateBalanceResponse fromModel(Balance balance) {
        return CreateBalanceResponse
                .builder()
                .value(balance.getValue())
                .currentExpense(balance.getCurrentExpense())
                .currentRate(balance.getCurrentRate())
                .currentType(balance.getCurrentType())
                .build();
    }
}
