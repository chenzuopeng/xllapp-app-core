package org.xllapp.app.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 执行一次的任务.
 * 
 * @author dylan.chen Aug 3, 2013
 * 
 */
public abstract class OnceTask implements Runnable {

	protected Logger logger;

	public OnceTask() {
		this.logger = LoggerFactory.getLogger(this.getClass());
	}

	protected abstract void doTask();

	@Override
	public void run() {
		try {
			this.logger.info("starting task");
			doTask();
			this.logger.info("completed task");
		} catch (Throwable throwable) {
			this.logger.error("failed task", throwable);
		}
	}

}
