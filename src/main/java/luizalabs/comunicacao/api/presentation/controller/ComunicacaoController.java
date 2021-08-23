package luizalabs.comunicacao.api.presentation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import luizalabs.comunicacao.api.domain.model.Comunicacao;
import luizalabs.comunicacao.api.infra.dto.ComunicacaoDTO;
import luizalabs.comunicacao.api.infra.service.IAgendamentoService;
import luizalabs.comunicacao.api.infra.service.IConsultarAgendamentoService;

@RestController
public class ComunicacaoController extends BaseController {

	public ComunicacaoController(IAgendamentoService agendamentoService, IConsultarAgendamentoService consultarService) {
		super(agendamentoService, consultarService);
	}

	@GetMapping("/hello-world")
	public String helloWorld() {
		return agendamentoService.helloWorld();
	}
	
	@GetMapping("/{id}")
	public Comunicacao recuperarComunicacao(@PathVariable Long id) {
		return consultarService.consultarAgendamento(id);
	};
	
	@PostMapping("/store")
	public Comunicacao agendarEnvioMensagem(ComunicacaoDTO comunicacao) throws Exception {
		return agendamentoService.criarAgendamento(comunicacao);
	}
	
}
