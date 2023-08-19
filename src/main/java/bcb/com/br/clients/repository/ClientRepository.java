package bcb.com.br.clients.repository;

import bcb.com.br.clients.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
