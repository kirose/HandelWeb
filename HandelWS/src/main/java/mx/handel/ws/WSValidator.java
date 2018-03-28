package mx.handel.ws;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.handel.cfg.dao.CfgParametroDAO;
import mx.handel.cfg.entity.CfgParametro;
import mx.handel.ws.dao.WSServicioDAO;
import mx.handel.ws.entity.WSServicio;
/**
 * WSValidator es nuestro contenedor de servicios
 * Se consultan solo una vez los servicios que se exponen y se almacenan en esta clase
 * @author Marco Antonio
 *
 */
public final class WSValidator {
	private Map<String,WSServicio> servicios = new HashMap<String,WSServicio>();	
	private static WSValidator instance;
	private Boolean reloadServicesOnNotFound;
	private WSServicioDAO wsServicioDAO;
	
	private WSValidator(WSServicioDAO wsServicioDAO, CfgParametroDAO cfgParametroDAO) {
		super();
		this.wsServicioDAO = wsServicioDAO;
		CfgParametro reload = cfgParametroDAO.find(CfgParametro.RELOAD_SERVICES_ON_NOT_FOUND);
		reloadServicesOnNotFound = reload == null ? false : "true".equals(reload.getValor());
		List<WSServicio> services = wsServicioDAO.findBy("visibilidad", "public");
		for (WSServicio s : services) {
			servicios.put(s.getUrl(), s);
		}
	}

	public static WSValidator getInstance(WSServicioDAO wsServicioDAO, CfgParametroDAO cfgParametroDAO){
		if (instance == null){
			instance = new WSValidator(wsServicioDAO,cfgParametroDAO);
		}
		return instance;
	}
	public boolean existService(String service){
		return servicios.containsKey(service);
	}
	public WSServicio getServicio(String service) {
		if (!servicios.containsKey(service) && reloadServicesOnNotFound){
			List<WSServicio> services = wsServicioDAO.findBy("visibilidad", "public");
			for (WSServicio s : services) {
				servicios.put(s.getUrl(), s);
			}
		}
		return servicios.get(service);
	}
}
