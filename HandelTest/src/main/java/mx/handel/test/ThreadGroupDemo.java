package mx.handel.test;


public class ThreadGroupDemo implements Runnable {
   public static void main(String[] args) {
      ThreadGroupDemo tg = new ThreadGroupDemo();
      tg.func();
   }

   public void func() {
      try {     
         // create a parent ThreadGroup
         ThreadGroup pGroup = new ThreadGroup("Parent ThreadGroup");
    
         // create a child ThreadGroup for parent ThreadGroup
         ThreadGroup cGroup = new ThreadGroup(pGroup, "Child ThreadGroup");

         // create a thread
         Thread t1 = new Thread(pGroup, this);
         System.out.println("Starting " + t1.getName() + "...");
         t1.start();
            
         // create another thread
         Thread t2 = new Thread(cGroup, this);
         System.out.println("Starting " + t2.getName() + "...");
         t2.start();

         // prints the parent ThreadGroup of both parent and child threads
         System.out.println(String.format("ParentThreadGroup for %s is %s",pGroup.getName(),pGroup.getParent().getName()));
         System.out.println(String.format("ParentThreadGroup for %s is %s",cGroup.getName(),cGroup.getParent().getName()));
         
         // block until the other threads finish
         t1.join();
         t2.join();
         Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				Thread ct = Thread.currentThread();
				ThreadGroup tg;
				while (!"main".equals((tg = ct.getThreadGroup()).getName())){}
				System.out.println("Main thread Group: "+ tg);
				ct = Thread.currentThread();
				System.out.println("Current thread: "+ct.getName());
				tg = ct.getThreadGroup();
				ThreadGroup pt = tg.getParent();
				System.out.println("Group thread: "+tg.getName());
				System.out.println("Parent thread: "+pt.getName());
			}
         });
         t.start();
      } catch (InterruptedException ex) {
         System.out.println(ex.toString());
      }
   }

   // implements run()
   public void run() {

      for(int i = 0;i < 1000;i++) {
         i++;
      }
      System.out.println(Thread.currentThread().getName() + " finished executing.");
   }
} 