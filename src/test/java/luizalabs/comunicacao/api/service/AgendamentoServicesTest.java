package luizalabs.comunicacao.api.service;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import javassist.NotFoundException;
import luizalabs.comunicacao.api.domain.enumeration.TipoComunicacao;
import luizalabs.comunicacao.api.domain.enumeration.TipoStatusMensagem;
import luizalabs.comunicacao.api.domain.model.Comunicacao;
import luizalabs.comunicacao.api.infra.dto.ComunicacaoDTO;
import luizalabs.comunicacao.api.infra.repository.ComunicacaoRepository;
import luizalabs.comunicacao.api.infra.service.IAgendamentoService;
import luizalabs.comunicacao.api.infra.service.IConsultarAgendamentoService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Profile("test")
public class AgendamentoServicesTest {
	
	@Autowired
	private ComunicacaoRepository repository;
	
	@Autowired
	private IAgendamentoService agendamentoService;
	
	@Autowired
	private IConsultarAgendamentoService consultarService;
	
	@Test
	@Order(1)
	void deveCadastrarUmAgendamentoPorNumero() {
		ComunicacaoDTO comunicacaoDTO = new ComunicacaoDTO(TipoComunicacao.WHATSAPP, "22 99871-1658", "Teste de mensagem", LocalDateTime.now().plusDays(15));
		Comunicacao c = agendamentoService.criarAgendamento(comunicacaoDTO);
		Assertions.assertNotNull(c);
		Assertions.assertEquals(TipoComunicacao.WHATSAPP, c.getTipoComunicacao());
	}
	
	@Test
	@Order(2)
	void deveCadastrarUmAgendamentoPorEmail() {
		ComunicacaoDTO comunicacaoDTO = new ComunicacaoDTO(TipoComunicacao.EMAIL, "joao@gmail.com", "Teste de mensagem por email", LocalDateTime.now().plusDays(15));
		Comunicacao c = agendamentoService.criarAgendamento(comunicacaoDTO);
		Assertions.assertNotNull(c);
		Assertions.assertEquals(TipoComunicacao.EMAIL, c.getTipoComunicacao());
	}

	@Test
	@Order(3)
	void deveRecuperarUmAgendamentoPorId() throws NotFoundException {
		ComunicacaoDTO comunicacaoDTO = new ComunicacaoDTO(TipoComunicacao.EMAIL, "joaotester@gmail.com", "Teste de mensagem por email", LocalDateTime.now().plusDays(15));
		Comunicacao c = agendamentoService.criarAgendamento(comunicacaoDTO);
		Comunicacao consulta = consultarService.consultarAgendamento(c.getId());
		Assertions.assertNotNull(consulta);
		Assertions.assertEquals("joaotester@gmail.com", c.getMensagem().getDestinatario());
	}
	
	@Test
	@Order(4)
	void deveAlterarOStatusParaCancelado() throws NotFoundException {
		ComunicacaoDTO comunicacaoDTO = new ComunicacaoDTO(TipoComunicacao.EMAIL, "joaotester@gmail.com", "Teste de mensagem por email", LocalDateTime.now().plusDays(15));
		Comunicacao c = agendamentoService.criarAgendamento(comunicacaoDTO);
		Comunicacao consulta = consultarService.consultarAgendamento(c.getId());
		Assertions.assertEquals(TipoStatusMensagem.ATIVO, c.getMensagem().getStatusMensagem());
		agendamentoService.cancelarAgendamento(c.getId());
		c = consultarService.consultarAgendamento(c.getId());
		Assertions.assertEquals(TipoStatusMensagem.CANCELADO, c.getMensagem().getStatusMensagem());
	}
}
