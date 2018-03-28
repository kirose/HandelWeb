package mx.handel.ws.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * @author Marco Antonio Salazar
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name = "usuario",schema = "ws")
@NamedQueries({
	@NamedQuery(name="WSUsuario.findAll", query="SELECT u FROM WSUsuario u"),
	@NamedQuery(name="WSUsuario.findSingleResultByIdUsuario", query="SELECT u FROM WSUsuario u WHERE u.usuario = :idUsuario"),
	@NamedQuery(name="WSUsuario.findSingleResultByUsuario", query="SELECT u FROM WSUsuario u WHERE u.usuario = :usuario"),
})
public class WSUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="WS_USUARIO_IDUSUARIO_GENERATOR", sequenceName="USUARIO_ID_USUARIO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_IDUSUARIO_GENERATOR")
	@Column(name="id_usuario")
	private Integer idUsuario;
	
	@Column(name="contrasenia")
	private String contrasenia;
	
	@Column(name="usuario")
	private String usuario;


	//bi-directional many-to-one association to UsuarioEstatus
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_estatus")
	private WSUsuarioEstatus usuarioEstatus;

	//bi-directional many-to-one association to UsuarioTipo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tipo")
	private WSUsuarioTipo usuarioTipo;
	/**
	 * El token que se genera al ser logearse al sistema el usuario
	 */
	@Transient
	private String token;
	/**
	 * El serviceKey para uso de la api
	 */
	@Transient
	private String serviceKey;
	/**
	 * El serviceKey para uso de la api
	 */
	@Transient
	private Date expiration;
	/**
	 * El UUDI se la session
	 */
	@Transient
	private String uudi;
	public WSUsuario() {
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public WSUsuarioEstatus getUsuarioEstatus() {
		return this.usuarioEstatus;
	}

	public void setUsuarioEstatus(WSUsuarioEstatus usuarioEstatus) {
		this.usuarioEstatus = usuarioEstatus;
	}

	public WSUsuarioTipo getUsuarioTipo() {
		return this.usuarioTipo;
	}

	public void setUsuarioTipo(WSUsuarioTipo usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getServiceKey() {
		return serviceKey;
	}

	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getUudi() {
		return uudi;
	}

	public void setUudi(String uudi) {
		this.uudi = uudi;
	}

}