package mx.handel.web.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIO_IDUSUARIO_GENERATOR", sequenceName="USUARIO_ID_USUARIO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_IDUSUARIO_GENERATOR")
	@Column(name="id_usuario")
	private Long idUsuario;

	private String celular;

	private String contrasenia;

	@Column(name="contrasenia_sha")
	private String contraseniaSha;

	@Column(name="email")
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="img_perfil")
	private String imgPerfil;

	@Column(name="nombre")
	private String nombre;

	@Column(name="usuario")
	private String usuario;

	//bi-directional one-to-one association to Cliente
	@OneToOne(mappedBy="usuario", fetch=FetchType.LAZY)
	private Cliente cliente;

	//bi-directional many-to-one association to DatoContacto
	@OneToMany(mappedBy="usuario")
	private List<DatoContacto> datoContactos;

	//bi-directional many-to-one association to MetodoPago
	@OneToMany(mappedBy="usuario")
	private List<MetodoPago> metodoPagos;

	//bi-directional one-to-one association to Proveedor
	@OneToOne(mappedBy="usuario", fetch=FetchType.LAZY)
	private Proveedor proveedor;

	//bi-directional many-to-one association to UsuarioEstatus
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_estatus")
	private UsuarioEstatus usuarioEstatus;

	//bi-directional many-to-one association to UsuarioTipo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tipo")
	private UsuarioTipo usuarioTipo;

	public Usuario() {
	}

	public Long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getContraseniaSha() {
		return this.contraseniaSha;
	}

	public void setContraseniaSha(String contraseniaSha) {
		this.contraseniaSha = contraseniaSha;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getImgPerfil() {
		return this.imgPerfil;
	}

	public void setImgPerfil(String imgPerfil) {
		this.imgPerfil = imgPerfil;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<DatoContacto> getDatoContactos() {
		return this.datoContactos;
	}

	public void setDatoContactos(List<DatoContacto> datoContactos) {
		this.datoContactos = datoContactos;
	}

	public DatoContacto addDatoContacto(DatoContacto datoContacto) {
		getDatoContactos().add(datoContacto);
		datoContacto.setUsuario(this);

		return datoContacto;
	}

	public DatoContacto removeDatoContacto(DatoContacto datoContacto) {
		getDatoContactos().remove(datoContacto);
		datoContacto.setUsuario(null);

		return datoContacto;
	}

	public List<MetodoPago> getMetodoPagos() {
		return this.metodoPagos;
	}

	public void setMetodoPagos(List<MetodoPago> metodoPagos) {
		this.metodoPagos = metodoPagos;
	}

	public MetodoPago addMetodoPago(MetodoPago metodoPago) {
		getMetodoPagos().add(metodoPago);
		metodoPago.setUsuario(this);

		return metodoPago;
	}

	public MetodoPago removeMetodoPago(MetodoPago metodoPago) {
		getMetodoPagos().remove(metodoPago);
		metodoPago.setUsuario(null);

		return metodoPago;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public UsuarioEstatus getUsuarioEstatus() {
		return this.usuarioEstatus;
	}

	public void setUsuarioEstatus(UsuarioEstatus usuarioEstatus) {
		this.usuarioEstatus = usuarioEstatus;
	}

	public UsuarioTipo getUsuarioTipo() {
		return this.usuarioTipo;
	}

	public void setUsuarioTipo(UsuarioTipo usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}

}