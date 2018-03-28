package mx.handel.web.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dato_contacto_tipo database table.
 * 
 */
@Entity
@Table(name="dato_contacto_tipo")
@NamedQuery(name="DatoContactoTipo.findAll", query="SELECT d FROM DatoContactoTipo d")
public class DatoContactoTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo")
	private Integer idTipo;

	private String tipo;

	//bi-directional many-to-one association to DatoContacto
	@OneToMany(mappedBy="datoContactoTipo")
	private List<DatoContacto> datoContactos;

	public DatoContactoTipo() {
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

	public List<DatoContacto> getDatoContactos() {
		return this.datoContactos;
	}

	public void setDatoContactos(List<DatoContacto> datoContactos) {
		this.datoContactos = datoContactos;
	}

	public DatoContacto addDatoContacto(DatoContacto datoContacto) {
		getDatoContactos().add(datoContacto);
		datoContacto.setDatoContactoTipo(this);

		return datoContacto;
	}

	public DatoContacto removeDatoContacto(DatoContacto datoContacto) {
		getDatoContactos().remove(datoContacto);
		datoContacto.setDatoContactoTipo(null);

		return datoContacto;
	}

}