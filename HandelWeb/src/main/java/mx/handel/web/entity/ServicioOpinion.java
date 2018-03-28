package mx.handel.web.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the servicio_opinion database table.
 * 
 */
@Entity
@Table(name="servicio_opinion")
@NamedQuery(name="ServicioOpinion.findAll", query="SELECT s FROM ServicioOpinion s")
public class ServicioOpinion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SERVICIO_OPINION_IDOPINION_GENERATOR", sequenceName="SERVICIO_OPINION_ID_OPINION_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SERVICIO_OPINION_IDOPINION_GENERATOR")
	@Column(name="id_opinion")
	private Long idOpinion;

	private float calificacion;

	private String opinion;

	//bi-directional many-to-one association to Servicio
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_servicio")
	private Servicio servicio;

	public ServicioOpinion() {
	}

	public Long getIdOpinion() {
		return this.idOpinion;
	}

	public void setIdOpinion(Long idOpinion) {
		this.idOpinion = idOpinion;
	}

	public float getCalificacion() {
		return this.calificacion;
	}

	public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
	}

	public String getOpinion() {
		return this.opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

}