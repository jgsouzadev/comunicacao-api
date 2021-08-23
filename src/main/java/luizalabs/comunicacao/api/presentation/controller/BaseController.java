package luizalabs.comunicacao.api.presentation.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import luizalabs.comunicacao.api.infra.service.IAgendamentoService;

@AllArgsConstructor
@RequestMapping("/api/")
public class BaseController {

	protected IAgendamentoService agendamentoService;
	
}
