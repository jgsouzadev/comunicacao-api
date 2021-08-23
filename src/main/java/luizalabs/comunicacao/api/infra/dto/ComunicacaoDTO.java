package luizalabs.comunicacao.api.infra.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import luizalabs.comunicacao.api.domain.enumeration.TipoComunicacao;

@Data
@AllArgsConstructor
public class ComunicacaoDTO {

	@ApiModelProperty(value = "O tipo da mensagem")
	private TipoComunicacao tipoComunicacao;
	
	@ApiModelProperty(value = "Quem irá receber a mensagem")
	private String destinatario;
	
	@ApiModelProperty(value = "O texto da mensagem")
	private String textoMensagem;
	
	@ApiModelProperty(value = "A data que a mensagem será enviada", example = "dd/MM/yyyy HH:mm:ss")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataEnvioMensagem;
	
}
