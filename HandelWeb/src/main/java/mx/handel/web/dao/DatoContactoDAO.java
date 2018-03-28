/**
 * 
 */
package mx.handel.web.dao;

import javax.ejb.Stateless;

import mx.handel.cfg.web.dao.AbstractDAO;
import mx.handel.web.entity.DatoContacto;

/**
 * @author Marco Antonio Salazar
 *
 */
@Stateless
public class DatoContactoDAO extends AbstractDAO<DatoContacto> {
	public DatoContactoDAO() {
		super(DatoContacto.class);
	}

}
