package mx.handel.web.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cliente_opinion database table.
 * 
 */
@Entity
@Table(name="cliente_opinion")
@NamedQuery(name="ClienteOpinion.findAll", query="SELECT c FROM ClienteOpinion c")
public class ClienteOpinion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLIENTE_OPINION_IDOPINION_GENERATOR", sequenceName="CLIENTE_OPINION_ID_OPINION_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLIENTE_OPINION_IDOPINION_GENERATOR")
	@Column(name="id_opinion")
	private Long idOpinion;

	private float calificacion;

	private String opinion;

	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Proveedor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;

	public ClienteOpinion() {
	}

	public Long getIdOpinion() {
		return this.idOpinion;
	}

	public void setIdOpinion(Long idOpinion) {
		this.idOpinion = idOpinion;
	}

	public float getCalificacion() {
		return this.calificacion;
	}

	public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
	}

	public String getOpinion() {
		return this.opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}