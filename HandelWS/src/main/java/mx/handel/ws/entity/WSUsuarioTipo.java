package mx.handel.ws.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * @author Marco Antonio Salazar
 * The persistent class for the usuario_tipo database table.
 * 
 */
@Entity
@Table(name="usuario_tipo",schema="ws")
@NamedQuery(name="WSUsuarioTipo.findAll", query="SELECT u FROM WSUsuarioTipo u")
public class WSUsuarioTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo")
	private Integer idTipo;

	private String tipo;

	//bi-directional many-to-many association to Perfil
	@ManyToMany
	@JoinTable(
		name="perfil_usuario_tipo"
		, schema="ws"
		, joinColumns={
			@JoinColumn(name="id_usuario_tipo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_perfil")
			}
		)
	private List<WSPerfil> perfiles;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="usuarioTipo")
	private List<WSUsuario> usuarios;

	//bi-directional many-to-one association to Permiso
	@OneToMany(mappedBy="usuarioTipo")
	private List<WSPermiso> permisos;

	public WSUsuarioTipo() {
	}

	public Integer getIdTipo() {
		return this.idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<WSPerfil> getPerfiles() {
		return this.perfiles;
	}

	public void setPerfiles(List<WSPerfil> perfils) {
		this.perfiles = perfils;
	}

	public List<WSUsuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<WSUsuario> usuarios) {
		this.usuarios = usuarios;
	}

	public WSUsuario addUsuario(WSUsuario usuario) {
		getUsuarios().add(usuario);
		usuario.setUsuarioTipo(this);

		return usuario;
	}

	public WSUsuario removeUsuario(WSUsuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setUsuarioTipo(null);

		return usuario;
	}

	public List<WSPermiso> getPermisos() {
		return this.permisos;
	}

	public void setPermisos(List<WSPermiso> permisos) {
		this.permisos = permisos;
	}

	public WSPermiso addPermiso(WSPermiso permiso) {
		getPermisos().add(permiso);
		permiso.setUsuarioTipo(this);

		return permiso;
	}

	public WSPermiso removePermiso(WSPermiso permiso) {
		getPermisos().remove(permiso);
		permiso.setUsuarioTipo(null);

		return permiso;
	}

}