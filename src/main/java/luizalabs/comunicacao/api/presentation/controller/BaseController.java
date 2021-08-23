package luizalabs.comunicacao.api.presentation.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import luizalabs.comunicacao.api.infra.service.IAgendamentoService;
import luizalabs.comunicacao.api.infra.service.IConsultarAgendamentoService;

@AllArgsConstructor
@RequestMapping("/api/comunicacao")
public class BaseController {

	protected IAgendamentoService agendamentoService;
	protected IConsultarAgendamentoService consultarService;
	
}
