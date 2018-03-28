package mx.handel.cfg.task;

import org.apache.log4j.Logger;

import mx.handel.cfg.pojo.Task;
/**
 * Una Tarea abstracta
 * Esta clase reprecenta una tare por ejecutar desde postgresql
 * @author Marco Antonio
 *
 */
public abstract class AbstractTask {
	protected Logger logger;
	
	public AbstractTask() {
		super();
		logger = Logger.getLogger(getClass());
	}

	abstract public void doTask(Task task);
}
