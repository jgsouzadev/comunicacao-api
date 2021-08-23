package luizalabs.comunicacao.api.infra.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import luizalabs.comunicacao.api.domain.model.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long>{

}
