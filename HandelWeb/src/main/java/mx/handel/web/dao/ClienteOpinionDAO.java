/**
 * 
 */
package mx.handel.web.dao;

import javax.ejb.Stateless;

import mx.handel.cfg.web.dao.AbstractDAO;
import mx.handel.web.entity.ClienteOpinion;

/**
 * @author Marco Antonio Salazar
 *
 */
@Stateless
public class ClienteOpinionDAO extends AbstractDAO<ClienteOpinion> {
	public ClienteOpinionDAO() {
		super(ClienteOpinion.class);
	}

}
