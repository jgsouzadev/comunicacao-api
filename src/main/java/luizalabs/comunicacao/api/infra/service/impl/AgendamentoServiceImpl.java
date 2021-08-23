package luizalabs.comunicacao.api.infra.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.regex.Matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import luizalabs.comunicacao.api.domain.enumeration.TipoComunicacao;
import luizalabs.comunicacao.api.domain.enumeration.TipoStatusMensagem;
import luizalabs.comunicacao.api.domain.model.Comunicacao;
import luizalabs.comunicacao.api.domain.model.Mensagem;
import luizalabs.comunicacao.api.infra.dto.ComunicacaoDTO;
import luizalabs.comunicacao.api.infra.handler.errors.DestinatarioInvalidoException;
import luizalabs.comunicacao.api.infra.handler.errors.ParametroInvalidoException;
import luizalabs.comunicacao.api.infra.repository.ComunicacaoRepository;
import luizalabs.comunicacao.api.infra.service.IAgendamentoService;
import luizalabs.comunicacao.api.infra.service.IConsultarAgendamentoService;

@Service
public class AgendamentoServiceImpl extends BaseServiceImpl implements IAgendamentoService{

	public AgendamentoServiceImpl(ComunicacaoRepository comunicacaoRepository) {
		super(comunicacaoRepository);
	}
	
	@Autowired
	private IConsultarAgendamentoService consultarService;

	@Override
	public Comunicacao criarAgendamento(ComunicacaoDTO comunicacaoDTO) throws RuntimeException {
		if(!this.validarTipoComunicacaoComMensagem(comunicacaoDTO))
			throw new DestinatarioInvalidoException("Destinatario não corresponde com o Tipo de Comunicação");
		
		return comunicacaoRepository.saveAndFlush(montarObjetoComunicacao(comunicacaoDTO));	
	}
	
	@Override
	public Boolean cancelarAgendamento(Long id) throws NotFoundException {

		Comunicacao comunicacao = consultarService.consultarAgendamento(id);
		comunicacao.getMensagem().setStatusMensagem(TipoStatusMensagem.CANCELADO);
		comunicacaoRepository.saveAndFlush(comunicacao);
		
		return true;
	}
	
	private Comunicacao montarObjetoComunicacao(ComunicacaoDTO comunicacao) {
		return Comunicacao.builder()
				.withDataEmissao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))
				.withMensagem(this.montarObjetoMensagem(comunicacao))
				.withTipoComunicacao(comunicacao.getTipoComunicacao())
				.build();
	}
	
	private Mensagem montarObjetoMensagem(ComunicacaoDTO c) {
		return new Mensagem(c.getTextoMensagem(), c.getDataEnvioMensagem(), c.getDestinatario(), TipoStatusMensagem.ATIVO);
	}

	private Boolean validarTipoComunicacaoComMensagem(ComunicacaoDTO c) {
		if(TipoComunicacao.EMAIL.equals(c.getTipoComunicacao()))
			this.validarEmail(c.getDestinatario());
		else 
			this.validarTelefone(c.getDestinatario());
			
		if(c.getDataEnvioMensagem().isBefore(LocalDateTime.now()))
			throw new ParametroInvalidoException("A data para o envio deve ser maior que a data de hoje");
		
		return true;
	}
	
	private void validarEmail(String email) {
		Matcher matcher = emailMatcher.matcher(email);
		if(!matcher.matches())
			throw new ParametroInvalidoException("O e-mail: " + email + " não é valido.");
	}
	
	private void validarTelefone(String telefone) {
		Matcher matcher = telefoneMatcher.matcher(telefone);
		if(!matcher.matches())
			throw new ParametroInvalidoException("O numero de telefone: " + telefone + " não é valido.");
	}
}
