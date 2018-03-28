package mx.handel.ws.dao;

import javax.ejb.Stateless;
import javax.persistence.StoredProcedureQuery;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mx.handel.cfg.ws.dao.AbstractDAO;
import mx.handel.cfg.ws.pojo.WSResponse;
import mx.handel.ws.entity.WSServicio;
/**
 * 
 * @author Marco Antonio Salazar
 *
 */
@Stateless
public class WSServicioDAO extends AbstractDAO<WSServicio>{
	public WSServicioDAO(){
		super(WSServicio.class);
	}
	public WSResponse invoke(Integer idUsuario, Integer idServicio, String params){

        StoredProcedureQuery query = getEntityManager().createNamedStoredProcedureQuery("WSServicio.invoke");
        query.setParameter("pid_usuario", idUsuario);
        query.setParameter("pid_servicio", idServicio);
        query.setParameter("pparams", params);
        query.setParameter("pdebug", false);
        query.execute();
        String jsonResponse = (String) query.getOutputParameterValue(5);
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(jsonResponse, WSResponse.class);
	}
}