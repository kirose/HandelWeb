package mx.handel.web.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the compra_estatus database table.
 * 
 */
@Entity
@Table(name="compra_estatus")
@NamedQuery(name="CompraEstatus.findAll", query="SELECT c FROM CompraEstatus c")
public class CompraEstatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_estatus")
	private Integer idEstatus;

	private String estatus;

	//bi-directional many-to-one association to Compra
	@OneToMany(mappedBy="compraEstatus")
	private List<Compra> compras;

	public CompraEstatus() {
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

	public List<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra addCompra(Compra compra) {
		getCompras().add(compra);
		compra.setCompraEstatus(this);

		return compra;
	}

	public Compra removeCompra(Compra compra) {
		getCompras().remove(compra);
		compra.setCompraEstatus(null);

		return compra;
	}

}