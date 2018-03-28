package mx.handel.cfg.ws.dao;

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
	private Class<T> entityClass;
	private Class<?> daoClass;
	private Logger logger;
    @PersistenceContext(unitName = "HandelPU")
    private EntityManager entityManager;
    
    protected Class<T> getEntityClass() {
		return entityClass;
	}
    protected Logger getLogger() {
		return logger;
	}
    protected EntityManager getEntityManager() {
		return entityManager;
	}
	public AbstractDAO(Class<T> clazz) {
		super();
		entityClass = clazz;
		daoClass = getClass();//.getGenericSuperclass().getClass();
		logger = Logger.getLogger(daoClass);
	}
	/**
	 * Entity.findAll = select e from Entity e 
	 * @return List<T>
	 */
	public List<T> findAll(){
		//logger.info(daoClass+".findAll");
		return entityManager.createNamedQuery(entityClass.getSimpleName()+".findAll", entityClass).getResultList();
	}
	/**
	 * Entity.findByParam = select e from Entity e where e.param = value 
	 * @param name nombre del parametro param
	 * @param value parameter value
	 * @return List<T>
	 */
	public List<T> findBy(String name, Object value){
		//logger.info(daoClass+".findBy"+StringUtils.capitalize(name)+"("+value+")");
		if (StringUtils.isBlank(name)){
			return null;
		}
		return entityManager.createNamedQuery(entityClass.getSimpleName()+".findBy"+StringUtils.capitalize(name), entityClass)
				.setParameter(name, value)
				.getResultList();
	}
	/**
	 * Entity.findSingleResultByParam = select e from Entity e where e.param = value 
	 * @param name nombre del parametro param
	 * @param value parameter value
	 * @return T
	 */
	public T findSingleResultBy(String name, Object value){
		//logger.info(daoClass+".findBy"+StringUtils.capitalize(name)+"("+value+")");
		if (StringUtils.isBlank(name)){
			return null;
		}
		try{
			return entityManager.createNamedQuery(entityClass.getSimpleName()+".findSingleResultBy"+StringUtils.capitalize(name), entityClass)
				.setParameter(name, value)
				.getSingleResult();
		} catch(javax.persistence.NonUniqueResultException e){
			return null;
		} catch(javax.persistence.NoResultException e){
			return null;
		}
	}
	/** 
	 * Find a entity by primary key
	 * @return T
	 */
	public T find(Object id){
		//logger.info(daoClass+".find("+id+")");
		return (T)entityManager.find(entityClass, id);
	}
	/**
	 * Persist entity
	 * @param instance
	 * @return T
	 */
	public T persist(T instance){
		//logger.info(daoClass+".persist("+instance+")");
		entityManager.persist(instance);
		return instance;
	}
	/**
	 * Delete entity
	 * @param instance
	 * @return boolean
	 */
	public boolean delete(T instance){
		//logger.info(daoClass+".delete("+instance+")");
		entityManager.remove(instance);
		return true;
	}
}
