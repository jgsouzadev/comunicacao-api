package luizalabs.comunicacao.api.infra.handler.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DestinatarioInvalidoException extends RuntimeException{
	
	public DestinatarioInvalidoException(String message) {
		super(message);
	}
	
}
