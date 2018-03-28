package mx.handel.web.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the compra database table.
 * 
 */
@Entity
@NamedQuery(name="Compra.findAll", query="SELECT c FROM Compra c")
public class Compra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="COMPRA_IDCOMPRA_GENERATOR", sequenceName="COMPRA_ID_COMPRA_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COMPRA_IDCOMPRA_GENERATOR")
	@Column(name="id_compra")
	private Long idCompra;

	@Column(name="calificada_cliente")
	private Boolean calificadaCliente;

	@Column(name="calificada_proveedor")
	private Boolean calificadaProveedor;

	private float cantidad;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_concluye")
	private Date fechaConcluye;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_solicitud")
	private Date fechaSolicitud;

	private double precio;

	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to CompraEstatus
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_estatus")
	private CompraEstatus compraEstatus;

	//bi-directional many-to-one association to MetodoPago
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_metodo_pago")
	private MetodoPago metodoPago;

	//bi-directional many-to-one association to Servicio
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_servicio")
	private Servicio servicio;

	//bi-directional one-to-one association to CompraCancelada
	@OneToOne(mappedBy="compra", fetch=FetchType.LAZY)
	private CompraCancelada compraCancelada;

	public Compra() {
	}

	public Long getIdCompra() {
		return this.idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public Boolean getCalificadaCliente() {
		return this.calificadaCliente;
	}

	public void setCalificadaCliente(Boolean calificadaCliente) {
		this.calificadaCliente = calificadaCliente;
	}

	public Boolean getCalificadaProveedor() {
		return this.calificadaProveedor;
	}

	public void setCalificadaProveedor(Boolean calificadaProveedor) {
		this.calificadaProveedor = calificadaProveedor;
	}

	public float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaConcluye() {
		return this.fechaConcluye;
	}

	public void setFechaConcluye(Date fechaConcluye) {
		this.fechaConcluye = fechaConcluye;
	}

	public Date getFechaSolicitud() {
		return this.fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public CompraEstatus getCompraEstatus() {
		return this.compraEstatus;
	}

	public void setCompraEstatus(CompraEstatus compraEstatus) {
		this.compraEstatus = compraEstatus;
	}

	public MetodoPago getMetodoPago() {
		return this.metodoPago;
	}

	public void setMetodoPago(MetodoPago metodoPago) {
		this.metodoPago = metodoPago;
	}

	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public CompraCancelada getCompraCancelada() {
		return this.compraCancelada;
	}

	public void setCompraCancelada(CompraCancelada compraCancelada) {
		this.compraCancelada = compraCancelada;
	}

}