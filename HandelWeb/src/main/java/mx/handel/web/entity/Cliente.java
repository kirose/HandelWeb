package mx.handel.web.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="id_cliente")
	private Integer idCliente;

	private float calificacion;

	@Column(name="no_calificaciones")
	private Integer noCalificaciones;

	@Column(name="no_cotizaciones")
	private Integer noCotizaciones;

	//bi-directional one-to-one association to Usuario
	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn(name="id_cliente")
	private Usuario usuario;

	//bi-directional many-to-one association to ClienteOpinion
	@OneToMany(mappedBy="cliente")
	private List<ClienteOpinion> clienteOpinions;

	//bi-directional many-to-one association to Compra
	@OneToMany(mappedBy="cliente")
	private List<Compra> compras;

	//bi-directional many-to-one association to Cotizacion
	@OneToMany(mappedBy="cliente")
	private List<Cotizacion> cotizacions;

	//bi-directional many-to-one association to Favorito
	@OneToMany(mappedBy="cliente")
	private List<Favorito> favoritos;

	public Cliente() {
	}

	public Integer getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
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

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ClienteOpinion> getClienteOpinions() {
		return this.clienteOpinions;
	}

	public void setClienteOpinions(List<ClienteOpinion> clienteOpinions) {
		this.clienteOpinions = clienteOpinions;
	}

	public ClienteOpinion addClienteOpinion(ClienteOpinion clienteOpinion) {
		getClienteOpinions().add(clienteOpinion);
		clienteOpinion.setCliente(this);

		return clienteOpinion;
	}

	public ClienteOpinion removeClienteOpinion(ClienteOpinion clienteOpinion) {
		getClienteOpinions().remove(clienteOpinion);
		clienteOpinion.setCliente(null);

		return clienteOpinion;
	}

	public List<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra addCompra(Compra compra) {
		getCompras().add(compra);
		compra.setCliente(this);

		return compra;
	}

	public Compra removeCompra(Compra compra) {
		getCompras().remove(compra);
		compra.setCliente(null);

		return compra;
	}

	public List<Cotizacion> getCotizacions() {
		return this.cotizacions;
	}

	public void setCotizacions(List<Cotizacion> cotizacions) {
		this.cotizacions = cotizacions;
	}

	public Cotizacion addCotizacion(Cotizacion cotizacion) {
		getCotizacions().add(cotizacion);
		cotizacion.setCliente(this);

		return cotizacion;
	}

	public Cotizacion removeCotizacion(Cotizacion cotizacion) {
		getCotizacions().remove(cotizacion);
		cotizacion.setCliente(null);

		return cotizacion;
	}

	public List<Favorito> getFavoritos() {
		return this.favoritos;
	}

	public void setFavoritos(List<Favorito> favoritos) {
		this.favoritos = favoritos;
	}

	public Favorito addFavorito(Favorito favorito) {
		getFavoritos().add(favorito);
		favorito.setCliente(this);

		return favorito;
	}

	public Favorito removeFavorito(Favorito favorito) {
		getFavoritos().remove(favorito);
		favorito.setCliente(null);

		return favorito;
	}

}