package bcb.com.br.clients.exception.handle;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorResponse {

  private List<Violation> violations = new ArrayList<>();
}

