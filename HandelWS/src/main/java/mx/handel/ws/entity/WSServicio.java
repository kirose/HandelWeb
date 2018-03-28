package mx.handel.ws.entity;

import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.OUT;
import java.util.List;


/**
 * @author Marco Antonio Salazar
 * The persistent class for the servicio database table.
 * 
 */
@Entity
@Table(name="servicio",schema="ws")
@NamedQueries({
	@NamedQuery(name="WSServicio.findAll", query="SELECT s FROM WSServicio s"),
	@NamedQuery(name="WSServicio.findByVisibilidad", query="SELECT s FROM WSServicio s where s.visibilidad = :visibilidad")
})
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "WSServicio.invoke",
        procedureName = "ws.invoke",
        parameters = {
            @StoredProcedureParameter(mode = IN, name = "pid_usuario", type = Integer.class),
            @StoredProcedureParameter(mode = IN, name = "pid_servicio", type = Integer.class),
            @StoredProcedureParameter(mode = IN, name = "pparams", type = String.class),
            @StoredProcedureParameter(mode = IN, name = "pdebug", type = Boolean.class),
            @StoredProcedureParameter(mode = OUT, name = "response", type = String.class)
        }
	),
})
public class WSServicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="WS_SERVICIO_IDSERVICIO_GENERATOR", sequenceName="SERVICIO_ID_SERVICIO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SERVICIO_IDSERVICIO_GENERATOR")
	@Column(name="id_servicio")
	private Integer idServicio;

	@Column(name="servicio")
	private String servicio;

	@Column(name="tipo")
	private String tipo;

	@Column(name="url")
	private String url;

	@Column(name="visibilidad")
	private String visibilidad;
	//bi-directional many-to-many association to Perfil
	@ManyToMany
	@JoinTable(
		name="servicio_perfil"
		, schema="ws"
		, joinColumns={
			@JoinColumn(name="id_servicio")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_perfil")
			}
		)
	private List<WSPerfil> perfiles;

	//bi-directional many-to-one association to Permiso
	@OneToMany(mappedBy="servicio")
	private List<WSPermiso> permisos;

	public WSServicio() {
	}

	public Integer getIdServicio() {
		return this.idServicio;
	}

	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
	}

	public String getServicio() {
		return this.servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<WSPerfil> getPerfiles() {
		return this.perfiles;
	}

	public void setPerfiles(List<WSPerfil> perfiles) {
		this.perfiles = perfiles;
	}

	public List<WSPermiso> getPermisos() {
		return this.permisos;
	}

	public void setPermisos(List<WSPermiso> permisos) {
		this.permisos = permisos;
	}

	public WSPermiso addPermiso(WSPermiso permiso) {
		getPermisos().add(permiso);
		permiso.setServicio(this);

		return permiso;
	}

	public WSPermiso removePermiso(WSPermiso permiso) {
		getPermisos().remove(permiso);
		permiso.setServicio(null);

		return permiso;
	}

	public String getVisibilidad() {
		return visibilidad;
	}

	public void setVisibilidad(String visibilidad) {
		this.visibilidad = visibilidad;
	}

}