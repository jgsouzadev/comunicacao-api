package luizalabs.comunicacao.api.infra.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import luizalabs.comunicacao.api.domain.model.Comunicacao;
import luizalabs.comunicacao.api.infra.repository.ComunicacaoRepository;
import luizalabs.comunicacao.api.infra.service.IConsultarAgendamentoService;

@Service
public class ConsultarAgendamentoServiceImpl extends BaseServiceImpl implements IConsultarAgendamentoService {

	public ConsultarAgendamentoServiceImpl(ComunicacaoRepository comunicacaoRepository) {
		super(comunicacaoRepository);
	}

	@Override
	public Comunicacao consultarAgendamento(Long id) throws NotFoundException {
		Optional<Comunicacao> comunicacao = comunicacaoRepository.findById(id);
		
		if(!comunicacao.isPresent())
			throw new NotFoundException("Não foi encontrado uma comunicação com esse id");
		
		return comunicacao.get();
	}

}
