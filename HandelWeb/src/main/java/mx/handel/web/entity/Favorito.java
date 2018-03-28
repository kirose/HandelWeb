package mx.handel.web.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the favorito database table.
 * 
 */
@Entity
@NamedQuery(name="Favorito.findAll", query="SELECT f FROM Favorito f")
public class Favorito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FAVORITO_IDFAVORITO_GENERATOR", sequenceName="FAVORITO_ID_FAVORITO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FAVORITO_IDFAVORITO_GENERATOR")
	@Column(name="id_favorito")
	private Long idFavorito;

	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Servicio
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_servicio")
	private Servicio servicio;

	public Favorito() {
	}

	public Long getIdFavorito() {
		return this.idFavorito;
	}

	public void setIdFavorito(Long idFavorito) {
		this.idFavorito = idFavorito;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

}