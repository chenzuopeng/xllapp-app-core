package org.xllapp.app.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 入口类.
 * 
 * @author dylan.chen Jun 17, 2013
 * 
 */
public class Main implements Runnable {

	private final static Logger logger = LoggerFactory.getLogger(Main.class);

	private ApplicationContext context;

	private void init() {
		logger.info("initializing Spring IoC container");
		this.context = new ClassPathXmlApplicationContext(new String[] { "classpath*:conf/applicationContext-*.xml","classpath*:/applicationContext-*.xml" });
		logger.info("initialized Spring IoC container");
	}
	
	private void processOnceTask(){
		OnceTaskExecutor onceTaskExecutor=(OnceTaskExecutor)this.context.getBean(OnceTaskExecutor.class);
		if(onceTaskExecutor!=null){
			logger.info("executing OnceTasks");
			onceTaskExecutor.execute();
			logger.info("executed OnceTasks");
		}
	}
	
	private void processScheduledTask(){
		ScheduledTaskExecutor scheduledTaskExecutor=(ScheduledTaskExecutor)this.context.getBean(ScheduledTaskExecutor.class);
	    if (scheduledTaskExecutor!=null) {
	    	logger.info("executing ScheduledTasks");
	    	scheduledTaskExecutor.execute();
	    	logger.info("executing ScheduledTasks");
		}
	}

	@Override
	public void run() {
		init();
		processScheduledTask();
		processOnceTask();
	}
	
}
