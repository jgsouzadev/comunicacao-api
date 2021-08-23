package luizalabs.comunicacao.api.infra.handler.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import luizalabs.comunicacao.api.domain.enumeration.TipoComunicacao;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ParametroInvalidoException extends IllegalArgumentException{
	
	public ParametroInvalidoException(String message) {
		super(message);
	}
	
}
