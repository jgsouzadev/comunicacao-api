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
import luizalabs.comunicacao.api.domain.enumeration.TipoComunicacao;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comunicacao {
	
	@Id
	@Column(name = "ID_COMUNICACAO")
	private Long id;
	
	@Column(name = "TP_COMUNICACAO")
	private TipoComunicacao tipoComunicacao;

	@Column(name = "DT_EMISSAO")
	private LocalDateTime dataEmissao;
	
	@Column(name = "DT_ALTERADO")
	private LocalDateTime dataAlterado;
	
	@JoinColumn(name = "ID_MENSAGEM")
	@OneToOne
	private Mensagem mensagem;
	
}
