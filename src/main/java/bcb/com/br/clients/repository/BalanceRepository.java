package bcb.com.br.clients.repository;

import bcb.com.br.clients.domain.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
}
