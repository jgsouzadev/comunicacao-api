package luizalabs.comunicacao.api.infra.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import luizalabs.comunicacao.api.infra.handler.errors.DestinatarioInvalidoException;
import luizalabs.comunicacao.api.infra.handler.errors.ParametroInvalidoException;

@ControllerAdvice
class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = DestinatarioInvalidoException.class)
	protected ResponseEntity<CustomThrowableErrorBody> handleInternalServerError(RuntimeException e, WebRequest request) {
		CustomThrowableErrorBody message = 
				new CustomThrowableErrorBody("Erro de validação", e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<CustomThrowableErrorBody>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = ParametroInvalidoException.class)
	protected ResponseEntity<CustomThrowableErrorBody> handleInternalServerError(IllegalArgumentException e, WebRequest request) {
		CustomThrowableErrorBody message = 
				new CustomThrowableErrorBody("Erro de validação", e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<CustomThrowableErrorBody>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = Exception.class)
	protected ResponseEntity<CustomThrowableErrorBody> handleInternalServerError(Exception e, WebRequest request) {
		CustomThrowableErrorBody message = 
				new CustomThrowableErrorBody("Erro não esperado no servidor", e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<CustomThrowableErrorBody>(message, HttpStatus.BAD_REQUEST);
	}
	
}