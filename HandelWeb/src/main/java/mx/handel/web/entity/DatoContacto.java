package mx.handel.web.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dato_contacto database table.
 * 
 */
@Entity
@Table(name="dato_contacto")
@NamedQuery(name="DatoContacto.findAll", query="SELECT d FROM DatoContacto d")
public class DatoContacto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DATO_CONTACTO_IDDATOCONTACTO_GENERATOR", sequenceName="DATO_CONTACTO_ID_DATO_CONTACTO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DATO_CONTACTO_IDDATOCONTACTO_GENERATOR")
	@Column(name="id_dato_contacto")
	private Long idDatoContacto;

	@Column(name="dato_contacto")
	private String datoContacto;

	//bi-directional many-to-one association to DatoContactoTipo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tipo")
	private DatoContactoTipo datoContactoTipo;

	//bi-directional many-to-one association to Usuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public DatoContacto() {
	}

	public Long getIdDatoContacto() {
		return this.idDatoContacto;
	}

	public void setIdDatoContacto(Long idDatoContacto) {
		this.idDatoContacto = idDatoContacto;
	}

	public String getDatoContacto() {
		return this.datoContacto;
	}

	public void setDatoContacto(String datoContacto) {
		this.datoContacto = datoContacto;
	}

	public DatoContactoTipo getDatoContactoTipo() {
		return this.datoContactoTipo;
	}

	public void setDatoContactoTipo(DatoContactoTipo datoContactoTipo) {
		this.datoContactoTipo = datoContactoTipo;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}