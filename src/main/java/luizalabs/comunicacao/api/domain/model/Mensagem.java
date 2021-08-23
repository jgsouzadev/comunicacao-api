package luizalabs.comunicacao.api.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import luizalabs.comunicacao.api.domain.enumeration.TipoStatusMensagem;

@Entity
@Data
public class Mensagem {
	
	public Mensagem(String texto, LocalDateTime dataEnvio, String destinatario, TipoStatusMensagem status) {
		this.dataEnvioMensagem = dataEnvio;
		this.destinatario = destinatario;
		this.textoMensagem = texto;
		this.statusMensagem = status;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MENSAGEM")
	private Long id;
	
	@Column(name = "TXT_MENSAGEM")
	private String textoMensagem;
	
	@Column(name = "CD_DESTINATARIO")
	private String destinatario;
	
	@Column(name = "DT_ENVIO")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dataEnvioMensagem;
	
	@Column(name = "TP_STATUS_MENSAGEM")
	private TipoStatusMensagem statusMensagem;
}
