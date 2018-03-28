package mx.handel.cfg;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.UnsupportedEncodingException;
import javax.security.auth.login.LoginException;

import org.apache.commons.lang3.StringUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;

import mx.handel.cfg.Authenticator;
import mx.handel.cfg.dao.CfgParametroDAO;
import mx.handel.cfg.entity.CfgParametro;
import mx.handel.utils.UtilCrypto;
import mx.handel.ws.dao.WSUsuarioDAO;
import mx.handel.ws.entity.WSUsuario;
/**
 * 
 * @author Marco Antonio Salazar
 *
 */
public final class Authenticator {
 
    private static Authenticator authenticator = null;// Singleton
    private final List<String> serviceKeysStorage = new ArrayList<String>();
    private final Map<String,WSUsuario> usuariosAutorizados = new HashMap<String,WSUsuario>();
    private long EXP_SESSION;
    private Algorithm algorithm = null;
    private WSUsuarioDAO usuarioDAO;
 
    /**
     * El constructor del autenticador
     * @param usuarioDAO
     * @param parametroDAO
     * @throws IllegalArgumentException
     * @throws UnsupportedEncodingException
     */
    private Authenticator(WSUsuarioDAO usuarioDAO, CfgParametroDAO parametroDAO) throws UnsupportedEncodingException {
        /**
         * Service keys are pre-generated by the system and is given to the
         * authorized client who wants to have access to the REST API. Here,
         * only username1 and username2 is given the REST service access with
         * their respective service keys.
         */
        serviceKeysStorage.add("f80ebc87-vf34-4b29-cR45-2345hhyt5434");
        serviceKeysStorage.add("3b95y6b8-926f-49b6-ba00-920bcnd3sc2a");
        
    	this.usuarioDAO = usuarioDAO;
        EXP_SESSION = Long.parseLong(parametroDAO.find(CfgParametro.EXPIRTION_SESSION_TIME).getValor())*1000L;
        algorithm = Algorithm.HMAC256("Handel-Secret");
    }
    /**
     * Singleton
     * @param usuarioDAO
     * @param parametroDAO
     * @return
     * @throws IllegalArgumentException
     * @throws UnsupportedEncodingException
     */
    public static Authenticator getInstance(WSUsuarioDAO usuarioDAO, CfgParametroDAO parametroDAO) throws UnsupportedEncodingException {
        if (authenticator == null) {
            authenticator = new Authenticator(usuarioDAO, parametroDAO);
        }
        return authenticator;
    }
    /**
     * Valida service_key, username y password
     * Si las validaciones son correctas crea un token con el cual se puede acceder al api
     * @param serviceKey
     * @param username
     * @param password
     * @return
     * @throws LoginException
     * @throws IllegalArgumentException
     * @throws UnsupportedEncodingException
     */
    public String login(String serviceKey, String username, String password) throws LoginException, IllegalArgumentException, UnsupportedEncodingException {
        if (serviceKeysStorage.contains(serviceKey)) {
            if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            	throw new LoginException("Credenciales Invalidas");
            }
            WSUsuario user = usuarioDAO.findSingleResultBy("usuario", username);
            if (user == null){
            	throw new LoginException("No existe Usuario: " +username);
            }
            if (user.getContrasenia().equals(UtilCrypto.sha256(password))) {
            	user.setUudi(UUID.randomUUID().toString());
            	//HMAC
            	String token = JWT.create()
            			.withClaim("service_key", serviceKey)
            			.withClaim("username", user.getUsuario())
            			//.withExpiresAt(new DateTime().plusSeconds(EXP_SESSION).toDate())
            			.withIssuer("Handel-Auth")
            			.withJWTId(user.getUudi())
            			.sign(algorithm);
            	Date now = new Date();
            	now.setTime(now.getTime() + EXP_SESSION);
            	user.setServiceKey(serviceKey);
				user.setToken(token);
				user.setExpiration(now);
				usuariosAutorizados.put(token, user);
				return token;
            } else {
            	throw new LoginException(String.format("Contraseña invalida(%s): %s",username,password));
            }
        }
        throw new LoginException("Service Key invalida" );
    }

    /**
     * The method that pre-validates if the client which invokes the REST API is
     * from a authorized and authenticated source.
     *
     * @param serviceKey The service key
     * @param authToken The authorization token generated after login
     * @return TRUE for acceptance and FALSE for denied.
     * @throws UnsupportedEncodingException 
     * @throws IllegalArgumentException 
     */
    public boolean isTokenValid(String token) throws IllegalArgumentException, UnsupportedEncodingException {

    	WSUsuario user = usuariosAutorizados.get(token);
        if (user == null) {
        	return false;
        }
        JWTVerifier verifier = JWT.require(algorithm)
        	.withClaim("service_key", user.getServiceKey())
        	.withClaim("username", user.getUsuario())
            .withIssuer("Handel-Auth")
            .withJWTId(user.getUudi())
            .build();
        verifier.verify(token);
        Date now = new Date();
        if (user.getExpiration().before(now)){
            logout(token);
        	throw new TokenExpiredException(String.format("El Token expiro %s.", user.getExpiration()));
        } else {
        	user.getExpiration().setTime(now.getTime()+EXP_SESSION);
        }
        return true;
    }
    /**
     * Cierra la sesion ws
     * @param token
     */
    public void logout(String token) {
        if (usuariosAutorizados.containsKey(token)) {
        	usuariosAutorizados.remove(token);
        }
    }
    public WSUsuario getAuthenticatedUserByToken(String token){
    	return usuariosAutorizados.get(token);
    }
}