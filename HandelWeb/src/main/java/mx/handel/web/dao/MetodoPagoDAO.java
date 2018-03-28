/**
 * 
 */
package mx.handel.web.dao;

import javax.ejb.Stateless;

import mx.handel.cfg.web.dao.AbstractDAO;
import mx.handel.web.entity.MetodoPago;

/**
 * @author Marco Antonio Salazar
 *
 */
@Stateless
public class MetodoPagoDAO extends AbstractDAO<MetodoPago> {
	public MetodoPagoDAO() {
		super(MetodoPago.class);
	}

}
