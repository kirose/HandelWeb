/**
 * 
 */
package mx.handel.web.dao;

import javax.ejb.Stateless;

import mx.handel.cfg.web.dao.AbstractDAO;
import mx.handel.web.entity.Usuario;

/**
 * @author Marco Antonio Salazar
 *
 */
@Stateless
public class UsuarioDAO extends AbstractDAO<Usuario> {
	public UsuarioDAO() {
		super(Usuario.class);
	}

}
