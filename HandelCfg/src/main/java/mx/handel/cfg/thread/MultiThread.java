package mx.handel.cfg.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
/**
 * Esta clase se usa para correr procesos en background
 * @author Marco Antonio
 *
 */
public final class MultiThread {

	/**
	 * The instance
	 */
	public static MultiThread instance;
	/**
	 * The pool of threads
	 */
	private ExecutorService threadPool;
	/**
	 * Simgleton
	 * @return instance
	 */
	public static MultiThread getInstance(){
		if (instance == null){
			instance = new MultiThread(); 
		}
		return instance;
	}
	private MultiThread(){
		ThreadGroup mainTG = findMainThreadGroup();
		if (mainTG == null){
			throw new NullPointerException();
		}
		// ************ Los hilos deben de ser hijos de main para que no sean daemon
		threadPool = Executors.newCachedThreadPool(new ThreadFactory(){
			@Override
			public Thread newThread(Runnable r){
				return new Thread(mainTG, r);
			}
		});
	}
	/**
	 * Este metodo ejecuta runnable en otro hilo
	 */
	public void execute(Runnable runnable){
		threadPool.submit(runnable);
	}
	/**
	 * Devuelve el Hilo princimal (main)
	 * @return
	 */
	private ThreadGroup findMainThreadGroup(){
		Thread ct = Thread.currentThread();
		ThreadGroup tg = ct.getThreadGroup();
		while (!"main".equals(tg.getName())){
			tg = tg.getParent();
		}
		return tg;
	}
}
