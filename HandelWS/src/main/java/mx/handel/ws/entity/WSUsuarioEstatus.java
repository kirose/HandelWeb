package mx.handel.ws.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * @author Marco Antonio Salazar
 * The persistent class for the usuario_estatus database table.
 * 
 */
@Entity
@Table(name="usuario_estatus",schema="ws")
@NamedQuery(name="WSUsuarioEstatus.findAll", query="SELECT u FROM WSUsuarioEstatus u")
public class WSUsuarioEstatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_estatus")
	private Integer idEstatus;

	private String estatus;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="usuarioEstatus")
	private List<WSUsuario> usuarios;

	public WSUsuarioEstatus() {
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

	public List<WSUsuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<WSUsuario> usuarios) {
		this.usuarios = usuarios;
	}

	public WSUsuario addUsuario(WSUsuario usuario) {
		getUsuarios().add(usuario);
		usuario.setUsuarioEstatus(this);

		return usuario;
	}

	public WSUsuario removeUsuario(WSUsuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setUsuarioEstatus(null);

		return usuario;
	}

}