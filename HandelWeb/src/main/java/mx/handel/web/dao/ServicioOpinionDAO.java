/**
 * 
 */
package mx.handel.web.dao;

import javax.ejb.Stateless;

import mx.handel.cfg.web.dao.AbstractDAO;
import mx.handel.web.entity.ServicioOpinion;

/**
 * @author Marco Antonio Salazar
 *
 */
@Stateless
public class ServicioOpinionDAO extends AbstractDAO<ServicioOpinion> {
	public ServicioOpinionDAO() {
		super(ServicioOpinion.class);
	}

}
