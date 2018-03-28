/**
 * 
 */
package mx.handel.web.dao;

import javax.ejb.Stateless;

import mx.handel.cfg.web.dao.AbstractDAO;
import mx.handel.web.entity.DatoContactoTipo;

/**
 * @author Marco Antonio Salazar
 *
 */
@Stateless
public class DatoContactoTipoDAO extends AbstractDAO<DatoContactoTipo> {
	public DatoContactoTipoDAO() {
		super(DatoContactoTipo.class);
	}

}
