/**
 * 
 */
package mx.handel.web.dao;

import javax.ejb.Stateless;

import mx.handel.cfg.web.dao.AbstractDAO;
import mx.handel.web.entity.Servicio;

/**
 * @author Marco Antonio Salazar
 *
 */
@Stateless
public class ServicioDAO extends AbstractDAO<Servicio> {
	public ServicioDAO() {
		super(Servicio.class);
	}

}
