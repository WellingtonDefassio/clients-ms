package bcb.com.br.clients.repository;

import bcb.com.br.clients.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> getClientByCnpj(String cnpj);

}
