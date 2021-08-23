package luizalabs.comunicacao.api.infra.service.impl;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import luizalabs.comunicacao.api.infra.repository.ComunicacaoRepository;

@AllArgsConstructor
@Service
public class BaseServiceImpl {

	protected ComunicacaoRepository comunicacaoRepository;
	protected static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	protected static final String TELEFONE_PATTERN = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})";
	protected static final Pattern emailMatcher = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
	protected static final Pattern telefoneMatcher = Pattern.compile(TELEFONE_PATTERN);

}
