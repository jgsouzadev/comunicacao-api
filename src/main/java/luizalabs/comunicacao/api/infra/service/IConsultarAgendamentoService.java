package luizalabs.comunicacao.api.infra.service;

import java.util.List;

import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import luizalabs.comunicacao.api.domain.model.Comunicacao;

@Service
public interface IConsultarAgendamentoService {
	
	Comunicacao consultarAgendamento(Long id) throws NotFoundException;

}
