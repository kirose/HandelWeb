/**
 * 
 */
package mx.handel.web.dao;

import javax.ejb.Stateless;

import mx.handel.cfg.web.dao.AbstractDAO;
import mx.handel.web.entity.Compra;

/**
 * @author Marco Antonio Salazar
 *
 */
@Stateless
public class CompraDAO extends AbstractDAO<Compra> {
	public CompraDAO() {
		super(Compra.class);
	}

}
