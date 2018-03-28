/**
 * 
 */
package mx.handel.web.dao;

import javax.ejb.Stateless;

import mx.handel.cfg.web.dao.AbstractDAO;
import mx.handel.web.entity.Cliente;

/**
 * @author Marco Antonio Salazar
 *
 */
@Stateless
public class ClienteDAO extends AbstractDAO<Cliente> {
	public ClienteDAO() {
		super(Cliente.class);
	}

}
