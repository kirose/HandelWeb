package mx.handel.ws.dao;

import javax.ejb.Stateless;

import mx.handel.cfg.ws.dao.AbstractDAO;
import mx.handel.ws.entity.WSUsuario;
/**
 * 
 * @author Marco Antonio Salazar
 *
 */
@Stateless
public class WSUsuarioDAO extends AbstractDAO<WSUsuario>{
	public WSUsuarioDAO(){
		super(WSUsuario.class);
	}
}
