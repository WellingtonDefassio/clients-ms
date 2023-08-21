package bcb.com.br.clients.controller.dto;

import lombok.Data;

@Data
public class ChangeLimitRequest {
    private String cnpj;
    private Double limit;

}
