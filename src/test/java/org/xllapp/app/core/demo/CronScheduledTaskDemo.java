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
@Service
public class CronScheduledTaskDemo extends ScheduledTask {
	
	@Override
	public String getCron() {
		return "0/5 * * * * *";
	}

	@Override
	public void doTask() {
        logger.debug("ScheduledTaskDemo.doTask()");
//        throw new RuntimeException();
/*        try {
			Thread.sleep(30*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	}

}
