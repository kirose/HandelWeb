package mx.handel.web.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the metodo_pago database table.
 * 
 */
@Entity
@Table(name="metodo_pago")
@NamedQuery(name="MetodoPago.findAll", query="SELECT m FROM MetodoPago m")
public class MetodoPago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="METODO_PAGO_IDMETODOPAGO_GENERATOR", sequenceName="METODO_PAGO_ID_METODO_PAGO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="METODO_PAGO_IDMETODOPAGO_GENERATOR")
	@Column(name="id_metodo_pago")
	private Long idMetodoPago;

	private String ccv;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_expiracion")
	private Date fechaExpiracion;

	@Column(name="no_tarjeta")
	private String noTarjeta;

	private String tipo;

	//bi-directional many-to-one association to Compra
	@OneToMany(mappedBy="metodoPago")
	private List<Compra> compras;

	//bi-directional many-to-one association to MetodoPagoEstatus
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_estatus")
	private MetodoPagoEstatus metodoPagoEstatus;

	//bi-directional many-to-one association to Usuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public MetodoPago() {
	}

	public Long getIdMetodoPago() {
		return this.idMetodoPago;
	}

	public void setIdMetodoPago(Long idMetodoPago) {
		this.idMetodoPago = idMetodoPago;
	}

	public String getCcv() {
		return this.ccv;
	}

	public void setCcv(String ccv) {
		this.ccv = ccv;
	}

	public Date getFechaExpiracion() {
		return this.fechaExpiracion;
	}

	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public String getNoTarjeta() {
		return this.noTarjeta;
	}

	public void setNoTarjeta(String noTarjeta) {
		this.noTarjeta = noTarjeta;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra addCompra(Compra compra) {
		getCompras().add(compra);
		compra.setMetodoPago(this);

		return compra;
	}

	public Compra removeCompra(Compra compra) {
		getCompras().remove(compra);
		compra.setMetodoPago(null);

		return compra;
	}

	public MetodoPagoEstatus getMetodoPagoEstatus() {
		return this.metodoPagoEstatus;
	}

	public void setMetodoPagoEstatus(MetodoPagoEstatus metodoPagoEstatus) {
		this.metodoPagoEstatus = metodoPagoEstatus;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}