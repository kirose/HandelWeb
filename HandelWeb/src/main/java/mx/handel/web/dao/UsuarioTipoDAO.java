/**
 * 
 */
package mx.handel.web.dao;

import javax.ejb.Stateless;

import mx.handel.cfg.web.dao.AbstractDAO;
import mx.handel.web.entity.UsuarioTipo;

/**
 * @author Marco Antonio Salazar
 *
 */
@Stateless
public class UsuarioTipoDAO extends AbstractDAO<UsuarioTipo> {
	public UsuarioTipoDAO() {
		super(UsuarioTipo.class);
	}

}
