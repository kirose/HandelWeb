/**
 * 
 */
package mx.handel.cfg.dao;

import mx.handel.cfg.web.dao.AbstractDAO;

import javax.ejb.Stateless;

import mx.handel.cfg.entity.CfgParametro;

/**
 * @author Marco Antonio Salazar
 *
 */
@Stateless
public class CfgParametroDAO extends AbstractDAO<CfgParametro> {
	public CfgParametroDAO() {
		super(CfgParametro.class);
	}
}
