package luizalabs.comunicacao.api.exception;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import javassist.NotFoundException;
import luizalabs.comunicacao.api.domain.enumeration.TipoComunicacao;
import luizalabs.comunicacao.api.infra.dto.ComunicacaoDTO;
import luizalabs.comunicacao.api.infra.handler.errors.ParametroInvalidoException;
import luizalabs.comunicacao.api.infra.service.IAgendamentoService;
import luizalabs.comunicacao.api.infra.service.IConsultarAgendamentoService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Profile("test")
public class ApiExceptionTest {
	@Autowired
	private IAgendamentoService agendamentoService;
	
	@Autowired
	private IConsultarAgendamentoService consultarService;
	
	@Test
	@Order(1)
	void deveLancarExcecaoPorNumeroErrado() {
		ComunicacaoDTO comunicacaoDTO = new ComunicacaoDTO(TipoComunicacao.WHATSAPP, "22 99871-18", "Teste de mensagem", LocalDateTime.now());
		Assertions.assertThrows(ParametroInvalidoException.class, () -> 
		agendamentoService.criarAgendamento(comunicacaoDTO));
	}
	
	@Test
	@Order(2)
	void deveLancarExcecaoPorEmailErrado() {
		ComunicacaoDTO comunicacaoDTO = new ComunicacaoDTO(TipoComunicacao.EMAIL, "abgmail.com", "Teste de mensagem", LocalDateTime.now());
		Assertions.assertThrows(ParametroInvalidoException.class, () -> 
		agendamentoService.criarAgendamento(comunicacaoDTO));
	}
	
	@Test
	@Order(3)
	void deveLancarExcecaoSeTentarCadastrarUmaDataJaUltrapassada() {
		ComunicacaoDTO comunicacaoDTO = 
				new ComunicacaoDTO(TipoComunicacao.EMAIL, "abc@gmail.com", "Teste de mensagem", LocalDateTime.now().minusDays(10L));
		Assertions.assertThrows(ParametroInvalidoException.class, () -> 
		agendamentoService.criarAgendamento(comunicacaoDTO));
	}
	
	@Test
	@Order(4)
	void deveLancarExcecaoSeNaoEncontrarUmAgendamento() {
		Assertions.assertThrows(NotFoundException.class, () -> 
		consultarService.consultarAgendamento(1502L));
	}

}
