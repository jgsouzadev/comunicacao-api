package luizalabs.comunicacao.api.infra.service;

import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import luizalabs.comunicacao.api.domain.model.Comunicacao;
import luizalabs.comunicacao.api.infra.dto.ComunicacaoDTO;

@Service
public interface IAgendamentoService {
	
	Comunicacao criarAgendamento(ComunicacaoDTO comunicacaoDTO) throws RuntimeException;
	
	Boolean cancelarAgendamento(Long id) throws NotFoundException;
}
