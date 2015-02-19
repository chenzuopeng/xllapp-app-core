package org.xllapp.app.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * 定时执行的任务.
 * 
 * @author dylan.chen Aug 3, 2013
 * 
 */
public abstract class ScheduledTask implements Runnable {

	private final static String LOG_MDC_TASK_SID = "SID";

	protected Logger logger;

	/**
	 * 执行次数.
	 */
	private long count = 1;

	/**
	 * 错误次数.
	 */
	private long errCount = 0;

	public ScheduledTask() {
		this.logger = LoggerFactory.getLogger(this.getClass());
	}

	public abstract void doTask();

	@Override
	public void run() {

		MDC.put(LOG_MDC_TASK_SID, Long.toString(count));

		try {

			logger.info("starting task[{}]", count);

			long startTime = System.currentTimeMillis();

			doTask();

			long elapsedTime = System.currentTimeMillis() - startTime;

			logger.info("completed task[{}],elapsed time: {} ms", count, elapsedTime);

		} catch (Throwable throwable) {

			errCount++;

			logger.error("failed task[" + count + "],error total: " + errCount, throwable);

		} finally {
			MDC.remove(LOG_MDC_TASK_SID);
			count++;
		}

	}

	/**
	 * 子类覆盖此方法,提供表示执行时间定义的Cron表达式.同时指示此定时任务的执行方式为基于cron表达式.
	 */
	public String getCron() {
		return null;
	}

	/**
	 * 子类覆盖此方法,用于设置两次任务执行的间隔时间(单位:毫秒).同时指示此定时任务的执行方式为以固定间隔时间的重复执行(
	 * 下一次的执行会在上一次执行完成后固定的时间间隔后开始).
	 * 
	 * 注:如果子类同时覆盖了getCron()方法,那么优先使用基于cron表达式方式的定时任务.
	 * 
	 */
	public long getDelay() {
		return -1;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ScheduledTask [cron=");
		builder.append(this.getCron());
		builder.append(", delay=");
		builder.append(this.getDelay());
		builder.append("]");
		return builder.toString();
	}

}
