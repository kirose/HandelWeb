package mx.handel.cfg.task;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.handel.cfg.pojo.Task;
import mx.handel.cfg.thread.MultiThread;

@Stateless
public class TaskManager {

	@Inject	private TaskEmail email;

	public void manage(List<Task> tasks){
		if (tasks == null || tasks.isEmpty()){
			return;
		}
		for (Task task : tasks) {
			if (task.getBackground()){
				/*
				 * Si se requiere que la tarea se ejecute en otro hilo 
				 */
				doTaskInBackground(task);
			} else {
				doTask(task);
			}
		}
	}
	private void doTask(Task task){
		if ("email".equals(task.getName())){
			email.doTask(task);
		}
	}
	private void doTaskInBackground(Task task){
		MultiThread.getInstance().execute(() -> {this.doTask(task);});
	}
}
