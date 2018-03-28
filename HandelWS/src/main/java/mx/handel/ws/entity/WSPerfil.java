package mx.handel.ws.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * @author Marco Antonio Salazar
 * The persistent class for the perfil database table.
 * 
 */
@Entity
@Table(name="perfil",schema="ws")
@NamedQuery(name="WSPerfil.findAll", query="SELECT p FROM WSPerfil p")
public class WSPerfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_perfil")
	private Integer idPerfil;

	private String perfil;

	//bi-directional many-to-many association to UsuarioTipo
	@ManyToMany(mappedBy="perfiles")
	private List<WSUsuarioTipo> usuarioTipos;

	//bi-directional many-to-many association to Servicio
	@ManyToMany(mappedBy="perfiles")
	private List<WSServicio> servicios;

	public WSPerfil() {
	}

	public Integer getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getPerfil() {
		return this.perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public List<WSUsuarioTipo> getUsuarioTipos() {
		return this.usuarioTipos;
	}

	public void setUsuarioTipos(List<WSUsuarioTipo> usuarioTipos) {
		this.usuarioTipos = usuarioTipos;
	}

	public List<WSServicio> getServicios() {
		return this.servicios;
	}

	public void setServicios(List<WSServicio> servicios) {
		this.servicios = servicios;
	}

}