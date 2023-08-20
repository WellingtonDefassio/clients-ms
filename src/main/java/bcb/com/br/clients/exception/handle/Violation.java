package bcb.com.br.clients.exception.handle;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Violation {
  private final String message;
}