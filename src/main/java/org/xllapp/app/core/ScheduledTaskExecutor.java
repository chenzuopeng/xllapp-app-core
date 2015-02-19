package org.xllapp.app.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

/**
 * 定时执行的任务的执行器.
 *
 * @author dylan.chen Aug 4, 2013
 * 
 */
public class ScheduledTaskExecutor implements ApplicationContextAware{
	
	private final static Logger logger = LoggerFactory.getLogger(ScheduledTaskExecutor.class);
	
	private List<ScheduledTask> scheduledTasks;
	
	private TaskScheduler taskScheduler;
	
	public void execute() {
		logger.info("ScheduledTask count:{}", this.scheduledTasks.size());
		for (ScheduledTask scheduledTask : this.scheduledTasks) {
			if(scheduledTask.getCron()!=null){
				logger.info("executing {} by cron", scheduledTask);
				this.taskScheduler.schedule(scheduledTask,new CronTrigger(scheduledTask.getCron()));
			}else if(scheduledTask.getDelay()>=0){
				logger.info("executing {} by delay", scheduledTask);
				this.taskScheduler.scheduleWithFixedDelay(scheduledTask, scheduledTask.getDelay());
			}else{
				logger.warn("invalid {}. because subclass must override getCron() or getDelay().", scheduledTask);
			}
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.scheduledTasks = new ArrayList<ScheduledTask>(applicationContext.getBeansOfType(ScheduledTask.class).values());
	}

	public void setTaskScheduler(TaskScheduler taskScheduler) {
		this.taskScheduler = taskScheduler;
	}
	
}
