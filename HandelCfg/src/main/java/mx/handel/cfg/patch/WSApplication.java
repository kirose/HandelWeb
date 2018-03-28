package mx.handel.cfg.patch;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ws.rs.core.Application;

import org.apache.log4j.Logger;

import mx.handel.cfg.ws.ctrl.WSAuthentication;
import mx.handel.utils.UtilReflection;
import mx.handel.ws.ctrl.WSServicioCtrl;
/**
 * 
 * @author Marco Antonio Salazar
 * Esta clase le indica a jersey cuales son lps web services
 * Los WebServices se deben de colocar en el paquete mx.handel.ws.ctrl 
 */
public class WSApplication extends Application 
{
	protected Logger logger = Logger.getLogger(getClass());
    public Set<Class<?>> getClasses()
    {
        Set<Class<?>> s = new HashSet<Class<?>>();
        
		try {
			List<Class<?>> classes = UtilReflection.getClasses("mx.handel.ws.ctrl");
			if (classes != null){
				s.addAll(classes);
				for (Class<?> c : classes) {
					logger.info("WS cargando controladores: " + c);
				}
			}
		} catch (ClassNotFoundException e) {
			logger.error("Error cargando clases de WebServic", e);
		} catch (IOException e) {
			logger.error("Error cargando clases de WebServic", e);
		}
        if (!s.contains(WSServicioCtrl.class)){
        	logger.info("No se encontro nada agregamos WSServicioCtrl.class");
            s.add(WSServicioCtrl.class);
        }
        if (!s.contains(WSAuthentication.class)){
        	logger.info("No se encontro nada agregamos WSAuthentication.class");
            s.add(WSAuthentication.class);
        }
        return s;
    }

}
