package mx.handel.ws.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * @author Marco Antonio Salazar
 * The persistent class for the permiso database table.
 * 
 */
@Entity
@Table(name = "permiso",schema = "ws")
@NamedQuery(name="WSPermiso.findAll", query="SELECT p FROM WSPermiso p")
public class WSPermiso implements Serializable {
	private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WSPermisoPK permisoPK;
	//bi-directional many-to-one association to UsuarioTipo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario_tipo",insertable=false,updatable=false)
	private WSUsuarioTipo usuarioTipo;

	//bi-directional many-to-one association to Servicio
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_servicio",insertable=false,updatable=false)
	private WSServicio servicio;

	public WSPermiso() {
	}

	public WSUsuarioTipo getUsuarioTipo() {
		return this.usuarioTipo;
	}

	public void setUsuarioTipo(WSUsuarioTipo usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}

	public WSServicio getServicio() {
		return this.servicio;
	}

	public void setServicio(WSServicio servicio) {
		this.servicio = servicio;
	}

}