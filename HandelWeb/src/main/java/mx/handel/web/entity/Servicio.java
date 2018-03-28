package mx.handel.web.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the servicio database table.
 * 
 */
@Entity
@Table(name = "servicio",schema = "public")
@NamedQuery(name="Servicio.findAll", query="SELECT s FROM Servicio s")
public class Servicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SERVICIO_IDSERVICIO_GENERATOR", sequenceName="SERVICIO_ID_SERVICIO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SERVICIO_IDSERVICIO_GENERATOR")
	@Column(name="id_servicio")
	private Long idServicio;

	private double precio;

	@Column(name="requiere_contizacion")
	private Boolean requiereContizacion;

	private String servicio;

	//bi-directional many-to-one association to Compra
	@OneToMany(mappedBy="servicio")
	private List<Compra> compras;

	//bi-directional many-to-one association to Cotizacion
	@OneToMany(mappedBy="servicio")
	private List<Cotizacion> cotizacions;

	//bi-directional many-to-one association to Favorito
	@OneToMany(mappedBy="servicio")
	private List<Favorito> favoritos;

	//bi-directional many-to-one association to Proveedor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;

	//bi-directional many-to-one association to ServicioOpinion
	@OneToMany(mappedBy="servicio")
	private List<ServicioOpinion> servicioOpinions;

	public Servicio() {
	}

	public Long getIdServicio() {
		return this.idServicio;
	}

	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Boolean getRequiereContizacion() {
		return this.requiereContizacion;
	}

	public void setRequiereContizacion(Boolean requiereContizacion) {
		this.requiereContizacion = requiereContizacion;
	}

	public String getServicio() {
		return this.servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public List<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra addCompra(Compra compra) {
		getCompras().add(compra);
		compra.setServicio(this);

		return compra;
	}

	public Compra removeCompra(Compra compra) {
		getCompras().remove(compra);
		compra.setServicio(null);

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
		cotizacion.setServicio(this);

		return cotizacion;
	}

	public Cotizacion removeCotizacion(Cotizacion cotizacion) {
		getCotizacions().remove(cotizacion);
		cotizacion.setServicio(null);

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
		favorito.setServicio(this);

		return favorito;
	}

	public Favorito removeFavorito(Favorito favorito) {
		getFavoritos().remove(favorito);
		favorito.setServicio(null);

		return favorito;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public List<ServicioOpinion> getServicioOpinions() {
		return this.servicioOpinions;
	}

	public void setServicioOpinions(List<ServicioOpinion> servicioOpinions) {
		this.servicioOpinions = servicioOpinions;
	}

	public ServicioOpinion addServicioOpinion(ServicioOpinion servicioOpinion) {
		getServicioOpinions().add(servicioOpinion);
		servicioOpinion.setServicio(this);

		return servicioOpinion;
	}

	public ServicioOpinion removeServicioOpinion(ServicioOpinion servicioOpinion) {
		getServicioOpinions().remove(servicioOpinion);
		servicioOpinion.setServicio(null);

		return servicioOpinion;
	}

}