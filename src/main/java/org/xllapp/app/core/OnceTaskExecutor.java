package org.xllapp.app.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 任务执行器.
 * 
 * @author dylan.chen Aug 4, 2013
 * 
 */
public class OnceTaskExecutor implements ApplicationContextAware {

	private final static Logger logger = LoggerFactory.getLogger(OnceTaskExecutor.class);

	private List<OnceTask> onceTasks;

	public void execute() {
		logger.info("OnceTask count:{}", this.onceTasks.size());
		for (OnceTask onceTask : this.onceTasks) {
			logger.info("executing {}", onceTask);
			new Thread(onceTask).start();
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.onceTasks = new ArrayList<OnceTask>(applicationContext.getBeansOfType(OnceTask.class).values());
	}

}
