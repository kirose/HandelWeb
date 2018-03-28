package mx.handel.cfg.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the parametro database table.
 * 
 */
@Entity
@Table(name = "parametro",schema = "cfg")
@NamedQueries({
	@NamedQuery(name="CfgParametro.findAll", query="SELECT p FROM CfgParametro p"),
	@NamedQuery(name="CfgParametro.findByTipo", query="SELECT p FROM CfgParametro p WHERE p.tipo = :tipo")
})
public class CfgParametro implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int EXPIRTION_SESSION_TIME = 13;
	public static final int DEBUG_ENABLE = 14;
	public static final int RELOAD_SERVICES_ON_NOT_FOUND = 15;
	@Id
	@Column(name="id_parametro")
	private Integer idParametro;
	@Column(name="tipo")
	private String tipo;
	@Column(name="parametro")
	private String parametro;
	@Column(name="valor")
	private String valor;

	public CfgParametro() {
	}

	public Integer getIdParametro() {
		return this.idParametro;
	}

	public void setIdParametro(Integer idParametro) {
		this.idParametro = idParametro;
	}

	public String getParametro() {
		return this.parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}