package mx.handel.web.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the metodo_pago_estatus database table.
 * 
 */
@Entity
@Table(name="metodo_pago_estatus")
@NamedQuery(name="MetodoPagoEstatus.findAll", query="SELECT m FROM MetodoPagoEstatus m")
public class MetodoPagoEstatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_estatus")
	private Integer idEstatus;

	private String estatus;

	//bi-directional many-to-one association to MetodoPago
	@OneToMany(mappedBy="metodoPagoEstatus")
	private List<MetodoPago> metodoPagos;

	public MetodoPagoEstatus() {
	}

	public Integer getIdEstatus() {
		return this.idEstatus;
	}

	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public List<MetodoPago> getMetodoPagos() {
		return this.metodoPagos;
	}

	public void setMetodoPagos(List<MetodoPago> metodoPagos) {
		this.metodoPagos = metodoPagos;
	}

	public MetodoPago addMetodoPago(MetodoPago metodoPago) {
		getMetodoPagos().add(metodoPago);
		metodoPago.setMetodoPagoEstatus(this);

		return metodoPago;
	}

	public MetodoPago removeMetodoPago(MetodoPago metodoPago) {
		getMetodoPagos().remove(metodoPago);
		metodoPago.setMetodoPagoEstatus(null);

		return metodoPago;
	}

}