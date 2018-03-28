/**
 * 
 */
package mx.handel.web.dao;

import javax.ejb.Stateless;

import mx.handel.cfg.web.dao.AbstractDAO;
import mx.handel.web.entity.Proveedor;

/**
 * @author Marco Antonio Salazar
 *
 */
@Stateless
public class ProveedorDAO extends AbstractDAO<Proveedor> {
	public ProveedorDAO() {
		super(Proveedor.class);
	}

}
