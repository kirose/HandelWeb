package mx.handel.web.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario_estatus database table.
 * 
 */
@Entity
@Table(name="usuario_estatus")
@NamedQuery(name="UsuarioEstatus.findAll", query="SELECT u FROM UsuarioEstatus u")
public class UsuarioEstatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_estatus")
	private Integer idEstatus;

	private String estatus;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="usuarioEstatus")
	private List<Usuario> usuarios;

	public UsuarioEstatus() {
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

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setUsuarioEstatus(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setUsuarioEstatus(null);

		return usuario;
	}

}