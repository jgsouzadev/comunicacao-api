package luizalabs.comunicacao.api.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import luizalabs.comunicacao.api.domain.model.Comunicacao;
import luizalabs.comunicacao.api.infra.dto.ComunicacaoDTO;
import luizalabs.comunicacao.api.infra.service.IAgendamentoService;

@RestController
public class ComunicacaoController extends BaseController {

	public ComunicacaoController(IAgendamentoService agendamentoService) {
		super(agendamentoService);
	}

	@GetMapping("/hello-world")
	public String helloWorld() {
		return agendamentoService.helloWorld();
	}
	
	@PostMapping("/store")
	public Comunicacao agendarEnvioMensagem(ComunicacaoDTO comunicacao) throws Exception {
		return agendamentoService.criarAgendamento(comunicacao);
	}
	
}
