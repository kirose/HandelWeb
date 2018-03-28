package mx.handel.web.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cotizacion database table.
 * 
 */
@Entity
@NamedQuery(name="Cotizacion.findAll", query="SELECT c FROM Cotizacion c")
public class Cotizacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="COTIZACION_IDCOTIZACION_GENERATOR", sequenceName="COTIZACION_ID_COTIZACION_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COTIZACION_IDCOTIZACION_GENERATOR")
	@Column(name="id_cotizacion")
	private Long idCotizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_captura")
	private Date fechaCaptura;

	private String precio;

	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to CotizacionEstatus
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_estatus")
	private CotizacionEstatus cotizacionEstatus;

	//bi-directional many-to-one association to Proveedor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;

	//bi-directional many-to-one association to Servicio
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_servicio")
	private Servicio servicio;

	public Cotizacion() {
	}

	public Long getIdCotizacion() {
		return this.idCotizacion;
	}

	public void setIdCotizacion(Long idCotizacion) {
		this.idCotizacion = idCotizacion;
	}

	public Date getFechaCaptura() {
		return this.fechaCaptura;
	}

	public void setFechaCaptura(Date fechaCaptura) {
		this.fechaCaptura = fechaCaptura;
	}

	public String getPrecio() {
		return this.precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public CotizacionEstatus getCotizacionEstatus() {
		return this.cotizacionEstatus;
	}

	public void setCotizacionEstatus(CotizacionEstatus cotizacionEstatus) {
		this.cotizacionEstatus = cotizacionEstatus;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

}