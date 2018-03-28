package mx.handel.web.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the compra_cancelada database table.
 * 
 */
@Entity
@Table(name="compra_cancelada")
@NamedQuery(name="CompraCancelada.findAll", query="SELECT c FROM CompraCancelada c")
public class CompraCancelada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="id_compra")
	private Integer idCompra;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_cancelada")
	private Date fechaCancelada;

	private String motivo;

	//bi-directional one-to-one association to Compra
	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn(name="id_compra")
	private Compra compra;

	public CompraCancelada() {
	}

	public Integer getIdCompra() {
		return this.idCompra;
	}

	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}

	public Date getFechaCancelada() {
		return this.fechaCancelada;
	}

	public void setFechaCancelada(Date fechaCancelada) {
		this.fechaCancelada = fechaCancelada;
	}

	public String getMotivo() {
		return this.motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Compra getCompra() {
		return this.compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

}