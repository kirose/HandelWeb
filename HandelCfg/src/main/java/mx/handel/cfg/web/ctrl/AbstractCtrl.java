package mx.handel.cfg.web.ctrl;

import org.apache.log4j.Logger;
/**
 * 
 * @author Marco Antonio Salazar
 *
 */
public class AbstractCtrl {
	protected Logger logger;
	public AbstractCtrl(){
		logger = Logger.getLogger(getClass());
	}
}
