/**
 * 
 */
package mx.handel.web.dao;

import javax.ejb.Stateless;

import mx.handel.cfg.web.dao.AbstractDAO;
import mx.handel.web.entity.UsuarioEstatus;

/**
 * @author Marco Antonio Salazar
 *
 */
@Stateless
public class UsuarioEstatusDAO extends AbstractDAO<UsuarioEstatus> {
	public UsuarioEstatusDAO() {
		super(UsuarioEstatus.class);
	}

}
