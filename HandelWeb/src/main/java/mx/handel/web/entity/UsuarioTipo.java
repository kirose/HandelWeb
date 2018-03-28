package mx.handel.web.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario_tipo database table.
 * 
 */
@Entity
@Table(name="usuario_tipo")
@NamedQuery(name="UsuarioTipo.findAll", query="SELECT u FROM UsuarioTipo u")
public class UsuarioTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo")
	private Integer idTipo;

	private String tipo;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="usuarioTipo")
	private List<Usuario> usuarios;

	public UsuarioTipo() {
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

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setUsuarioTipo(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setUsuarioTipo(null);

		return usuario;
	}

}