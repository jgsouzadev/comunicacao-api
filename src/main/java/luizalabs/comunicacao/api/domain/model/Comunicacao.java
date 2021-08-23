package luizalabs.comunicacao.api.domain.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import luizalabs.comunicacao.api.domain.enumeration.TipoComunicacao;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@Data
public class Comunicacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_COMUNICACAO")
	private Long id;
	
	@Column(name = "TP_COMUNICACAO")
	private TipoComunicacao tipoComunicacao;

	@Column(name = "DT_EMISSAO")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dataEmissao;
	
	@Column(name = "DT_ALTERADO")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dataAlterado;
	
	@JoinColumn(name = "ID_MENSAGEM")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Mensagem mensagem;
	
}
