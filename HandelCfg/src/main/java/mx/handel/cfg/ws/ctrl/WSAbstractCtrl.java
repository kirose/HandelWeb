package mx.handel.cfg.ws.ctrl;

import java.io.UnsupportedEncodingException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;


import mx.handel.cfg.Authenticator;
import mx.handel.cfg.dao.CfgParametroDAO;
import mx.handel.cfg.entity.CfgParametro;
import mx.handel.ws.dao.WSUsuarioDAO;
import mx.handel.ws.entity.WSUsuario;
/**
 * 
 * @author Marco Antonio Salazar
 *
 */
public abstract class WSAbstractCtrl {

	private Logger logger;
	
    @Context private UriInfo uriInfo;
    @Context private Request request;
    @Context private HttpHeaders httpHeaders;
    @Context private Application application;
    @Inject private HttpServletRequest servletRequest;

    private static Boolean debug;
    
    public WSAbstractCtrl(){
    	logger = Logger.getLogger(getClass());
    }
    
    @EJB protected WSUsuarioDAO wsUsuarioDAO;
    @EJB protected CfgParametroDAO cfgParametroDAO;
    
    public Logger getLogger() {
		return logger;
	}
	protected WSUsuario getAuthenticatedWSUsuario() {
    	String token = getServletRequest().getHeader("token");
    	try {
			return Authenticator.getInstance(wsUsuarioDAO, cfgParametroDAO).getAuthenticatedUserByToken(token);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
    }

	public UriInfo getUriInfo() {
		return uriInfo;
	}

	public Request getRequest() {
		return request;
	}

	public HttpHeaders getHttpHeaders() {
		return httpHeaders;
	}

	public Application getApplication() {
		return application;
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}
    protected Response.ResponseBuilder getNoCacheResponseBuilder( Response.Status status ) {
        CacheControl cc = new CacheControl();
        cc.setNoCache( true );
        cc.setMaxAge( -1 );
        cc.setMustRevalidate( true );
 
        return Response.status( status ).cacheControl( cc );
    }
    public Boolean isDebugEnabled(){
    	if (debug == null){
    		CfgParametro pdebug = cfgParametroDAO.find(CfgParametro.DEBUG_ENABLE);
    		debug = "true".equals(pdebug.getValor()) || "TRUE".equals(pdebug.getValor()) || "1".equals(pdebug.getValor());
    	}
    	return debug;
    }
}
