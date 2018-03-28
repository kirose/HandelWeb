package mx.handel.web.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the proveedor database table.
 * 
 */
@Entity
@NamedQuery(name="Proveedor.findAll", query="SELECT p FROM Proveedor p")
public class Proveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="id_proveedor")
	private Integer idProveedor;

	private float calificacion;

	@Column(name="no_calificaciones")
	private Integer noCalificaciones;

	@Column(name="no_cotizaciones")
	private Integer noCotizaciones;

	//bi-directional many-to-one association to ClienteOpinion
	@OneToMany(mappedBy="proveedor")
	private List<ClienteOpinion> clienteOpinions;

	//bi-directional many-to-one association to Cotizacion
	@OneToMany(mappedBy="proveedor")
	private List<Cotizacion> cotizacions;

	//bi-directional one-to-one association to Usuario
	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn(name="id_proveedor")
	private Usuario usuario;

	//bi-directional many-to-one association to Servicio
	@OneToMany(mappedBy="proveedor")
	private List<Servicio> servicios;

	public Proveedor() {
	}

	public Integer getIdProveedor() {
		return this.idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public float getCalificacion() {
		return this.calificacion;
	}

	public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
	}

	public Integer getNoCalificaciones() {
		return this.noCalificaciones;
	}

	public void setNoCalificaciones(Integer noCalificaciones) {
		this.noCalificaciones = noCalificaciones;
	}

	public Integer getNoCotizaciones() {
		return this.noCotizaciones;
	}

	public void setNoCotizaciones(Integer noCotizaciones) {
		this.noCotizaciones = noCotizaciones;
	}

	public List<ClienteOpinion> getClienteOpinions() {
		return this.clienteOpinions;
	}

	public void setClienteOpinions(List<ClienteOpinion> clienteOpinions) {
		this.clienteOpinions = clienteOpinions;
	}

	public ClienteOpinion addClienteOpinion(ClienteOpinion clienteOpinion) {
		getClienteOpinions().add(clienteOpinion);
		clienteOpinion.setProveedor(this);

		return clienteOpinion;
	}

	public ClienteOpinion removeClienteOpinion(ClienteOpinion clienteOpinion) {
		getClienteOpinions().remove(clienteOpinion);
		clienteOpinion.setProveedor(null);

		return clienteOpinion;
	}

	public List<Cotizacion> getCotizacions() {
		return this.cotizacions;
	}

	public void setCotizacions(List<Cotizacion> cotizacions) {
		this.cotizacions = cotizacions;
	}

	public Cotizacion addCotizacion(Cotizacion cotizacion) {
		getCotizacions().add(cotizacion);
		cotizacion.setProveedor(this);

		return cotizacion;
	}

	public Cotizacion removeCotizacion(Cotizacion cotizacion) {
		getCotizacions().remove(cotizacion);
		cotizacion.setProveedor(null);

		return cotizacion;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Servicio> getServicios() {
		return this.servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Servicio addServicio(Servicio servicio) {
		getServicios().add(servicio);
		servicio.setProveedor(this);

		return servicio;
	}

	public Servicio removeServicio(Servicio servicio) {
		getServicios().remove(servicio);
		servicio.setProveedor(null);

		return servicio;
	}

}