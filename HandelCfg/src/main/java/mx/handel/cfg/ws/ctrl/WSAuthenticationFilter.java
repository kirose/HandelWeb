package mx.handel.cfg.ws.ctrl;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import mx.handel.cfg.ws.ctrl.WSAbstractCtrl;
import mx.handel.cfg.ws.json.Json;
import mx.handel.cfg.ws.pojo.WSResponse;
import mx.handel.cfg.Authenticator;

/**
 * 
 * @author Marco Antonio Salazar
 *
 */
@Provider
public class WSAuthenticationFilter extends WSAbstractCtrl implements ContainerRequestFilter{ 
	
	public ContainerRequest filter(ContainerRequest request) {
		String url = getServletRequest().getRequestURI();
		if (url.startsWith("/Handel/ws/Authentication/")){
			//logger.info("Autentificando ...");
			return request;
		}
		boolean isTokenValid = false;
		String token = getServletRequest().getHeader("token"); 
		try {
			isTokenValid = Authenticator.getInstance(wsUsuarioDAO, cfgParametroDAO)
					.isTokenValid(token);
		} catch(TokenExpiredException e){
            throw new WebApplicationException(getNoCacheResponseBuilder(Response.Status.OK)
            		.entity(new Json()
            				.add("message", "El token ha expirado")
            				.add("status", WSResponse.ERROR_EXIRED_TOKEN)
            				.toString()
            		).build());
		} catch(InvalidClaimException e){
			getLogger().error("Error al validar Token: "+token,e);
            throw new WebApplicationException(getNoCacheResponseBuilder(Response.Status.UNAUTHORIZED)
            		.entity(new Json()
        				.add("message", "Token invalido")
        				.add("status", WSResponse.UNAUTHORIZED)
        				.toString()
            		).build());
		} catch (Exception e) {
			getLogger().error("Error al validar Token: "+token,e);
            throw new WebApplicationException(getNoCacheResponseBuilder(Response.Status.INTERNAL_SERVER_ERROR)
        		.entity(new Json()
    				.add("message", "Error Interno al validar Token")
    				.add("status", WSResponse.ERROR_INTERNAL)
    				.toString()
        		).build());
		}
		if (isTokenValid){
			//logger.info("Autentificado, procesando solicitud ...");
			return request;	
		}
		getLogger().info("Tratando de acceder sin Autentificacion");
		throw new WebApplicationException(Response.status(Response.Status.FORBIDDEN).build());

	}

}
