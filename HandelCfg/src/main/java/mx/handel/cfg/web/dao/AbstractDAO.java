package mx.handel.cfg.web.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import org.apache.log4j.Logger;
/**
 * 
 * @author Marco Antonio Salazar
 *
 * @param <T>
 */
public abstract class AbstractDAO <T>{
	protected Class<T> entityClass;
	protected Class<?> daoClass;
	protected Logger logger;
    @PersistenceContext(unitName = "HandelPU")
    private EntityManager em;
	public AbstractDAO(Class<T> clazz) {
		super();
		entityClass = clazz;
		daoClass = getClass();//.getGenericSuperclass().getClass();
		logger = Logger.getLogger(daoClass);
	}
	/**
	 * Entity.findAll = select e from Entity e 
	 * @return
	 */
	public List<T> findAll(){
		//logger.info(daoClass+".findAll");
		return em.createNamedQuery(entityClass.getSimpleName()+".findAll", entityClass).getResultList();
	}
	/**
	 * Entity.findByParam = select e from Entity e where e.param = value 
	 * @param name nombre del parametro param
	 * @param value parameter value
	 * @return
	 */
	public List<T> findBy(String name, Object value){
		//logger.info(daoClass+".findBy"+StringUtils.capitalize(name)+"("+value+")");
		if (StringUtils.isBlank(name)){
			return null;
		}
		return em.createNamedQuery(entityClass.getSimpleName()+".findBy"+StringUtils.capitalize(name), entityClass)
				.setParameter(name, value)
				.getResultList();
	}
	/** 
	 * Find a entity by primary key
	 * @return
	 */
	public T find(Object id){
		//logger.info(daoClass+".find("+id+")");
		return (T)em.find(entityClass, id);
	}
	/**
	 * Persist entity
	 * @param instance
	 * @return
	 */
	public T persist(T instance){
		//logger.info(daoClass+".persist("+instance+")");
		em.persist(instance);
		return instance;
	}
	/**
	 * Delete entity
	 * @param instance
	 * @return
	 */
	public boolean delete(T instance){
		//logger.info(daoClass+".delete("+instance+")");
		em.remove(instance);
		return true;
	}
}
