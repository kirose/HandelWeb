package mx.handel.web.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cotizacion_estatus database table.
 * 
 */
@Entity
@Table(name="cotizacion_estatus")
@NamedQuery(name="CotizacionEstatus.findAll", query="SELECT c FROM CotizacionEstatus c")
public class CotizacionEstatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_estatus")
	private Integer idEstatus;

	private String estatus;

	//bi-directional many-to-one association to Cotizacion
	@OneToMany(mappedBy="cotizacionEstatus")
	private List<Cotizacion> cotizacions;

	public CotizacionEstatus() {
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

	public List<Cotizacion> getCotizacions() {
		return this.cotizacions;
	}

	public void setCotizacions(List<Cotizacion> cotizacions) {
		this.cotizacions = cotizacions;
	}

	public Cotizacion addCotizacion(Cotizacion cotizacion) {
		getCotizacions().add(cotizacion);
		cotizacion.setCotizacionEstatus(this);

		return cotizacion;
	}

	public Cotizacion removeCotizacion(Cotizacion cotizacion) {
		getCotizacions().remove(cotizacion);
		cotizacion.setCotizacionEstatus(null);

		return cotizacion;
	}

}