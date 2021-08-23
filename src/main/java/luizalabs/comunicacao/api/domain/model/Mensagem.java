package luizalabs.comunicacao.api.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import luizalabs.comunicacao.api.domain.model.enumeration.TipoStatusMensagem;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Mensagem {
	
	@Id
	@Column(name = "ID_MENSAGEM")
	private Long id;
	
	@Column(name = "TXT_MENSAGEM")
	private String textoMensagem;
	
	@Column(name = "CD_DESTINATARIO")
	private String destinatario;
	
	@Column(name = "DT_ENVIO")
	private LocalDateTime dataEnvioMensagem;
	
	@Column(name = "TP_STATUS_MENSAGEM")
	private TipoStatusMensagem statusMensagem;
	
	@JoinColumn(name = "ID_COMUNICACAO")
	@OneToOne
	private Comunicacao comunicacao;
}
