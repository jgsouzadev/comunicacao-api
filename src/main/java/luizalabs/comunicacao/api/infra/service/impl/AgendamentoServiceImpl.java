package luizalabs.comunicacao.api.infra.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import luizalabs.comunicacao.api.domain.enumeration.TipoComunicacao;
import luizalabs.comunicacao.api.domain.enumeration.TipoStatusMensagem;
import luizalabs.comunicacao.api.domain.model.Comunicacao;
import luizalabs.comunicacao.api.domain.model.Mensagem;
import luizalabs.comunicacao.api.infra.dto.ComunicacaoDTO;
import luizalabs.comunicacao.api.infra.handler.errors.DestinatarioInvalidoException;
import luizalabs.comunicacao.api.infra.handler.errors.ParametroInvalidoException;
import luizalabs.comunicacao.api.infra.repository.ComunicacaoRepository;
import luizalabs.comunicacao.api.infra.service.IAgendamentoService;

@Service
@AllArgsConstructor
public class AgendamentoServiceImpl implements IAgendamentoService{

	private ComunicacaoRepository comunicacaoRepository;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String TELEFONE_PATTERN = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})";
	private static final Pattern emailMatcher = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
	private static final Pattern telefoneMatcher = Pattern.compile(TELEFONE_PATTERN);

	@Override
	public String helloWorld() {
		return "Hello World by Service";
	}

	@Override
	public Comunicacao criarAgendamento(ComunicacaoDTO comunicacaoDTO) throws Exception {
		if(!this.validarTipoComunicacaoComMensagem(comunicacaoDTO))
			throw new DestinatarioInvalidoException("Destinatario não corresponde com o Tipo de Comunicação");
		
		return comunicacaoRepository.saveAndFlush(montarObjetoComunicacao(comunicacaoDTO));	
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
