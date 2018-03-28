package mx.handel.ws.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * @author Marco Antonio Salazar
 * The persistent class for the servicio_tipo database table.
 * 
 */
@Entity
@Table(name="servicio_tipo",schema="ws")
@NamedQuery(name="ServicioTipo.findAll", query="SELECT s FROM WSServicioTipo s")
public class WSServicioTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo")
	private Integer idTipo;

	private String tipo;

	public WSServicioTipo() {
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

}