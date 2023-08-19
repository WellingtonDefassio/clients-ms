package bcb.com.br.clients.controller.handle;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Violation {
  private final String fieldName;
  private final String message;
}