/**
 * 
 */
package mx.handel.web.dao;

import javax.ejb.Stateless;

import mx.handel.cfg.web.dao.AbstractDAO;
import mx.handel.web.entity.Favorito;

/**
 * @author Marco Antonio Salazar
 *
 */
@Stateless
public class FavoritoDAO extends AbstractDAO<Favorito> {
	public FavoritoDAO() {
		super(Favorito.class);
	}

}
