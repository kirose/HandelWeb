package mx.handel.cfg.cl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MultithreadContextListener implements ServletContextListener {
	// https://stackoverflow.com/questions/791986/background-thread-for-a-tomcat-servlet-app
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}
