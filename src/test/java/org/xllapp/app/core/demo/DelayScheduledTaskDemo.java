package org.xllapp.app.core.demo;

import org.springframework.stereotype.Service;
import org.xllapp.app.core.ScheduledTask;

/**
 *
 *
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved 
 * @Company: 北京福富软件有限公司 
 * @author 陈作朋 Aug 3, 2013
 * @version 1.00.00
 * @history:
 * 
 */
//@Service
public class DelayScheduledTaskDemo extends ScheduledTask {

	@Override
	public long getDelay() {
		return 2000;
	}

	@Override
	public void doTask() {
        logger.debug("ScheduledTaskDemo.doTask()");
        try {
			Thread.sleep(1*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
