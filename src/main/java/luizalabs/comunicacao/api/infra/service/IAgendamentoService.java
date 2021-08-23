package luizalabs.comunicacao.api.infra.service;

import org.springframework.stereotype.Service;

import luizalabs.comunicacao.api.domain.model.Comunicacao;
import luizalabs.comunicacao.api.infra.dto.ComunicacaoDTO;

@Service
public interface IAgendamentoService {
	String helloWorld();
	
	Comunicacao criarAgendamento(ComunicacaoDTO comunicacaoDTO) throws Exception;
}
