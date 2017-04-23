package br.com.sociotorcedor.repository;

import br.com.sociotorcedor.domain.SocioTorcedor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Contrato com as operações de persistência sob o documento sociotorcedor
 * @author : Claudio Nazareth  chtnazareth@gmail.com
 */

@Repository
public interface SocioTorcedorRepository extends MongoRepository<SocioTorcedor, String> {

    Optional<SocioTorcedor> findByNomeCompletoIgnoreCase(String nomeCompleto);

}
