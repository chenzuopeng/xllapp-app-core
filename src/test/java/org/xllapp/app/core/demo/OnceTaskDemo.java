package org.xllapp.app.core.demo;

import org.springframework.stereotype.Service;
import org.xllapp.app.core.OnceTask;

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
public class OnceTaskDemo extends OnceTask {

	@Override
	public void doTask() {
		 logger.debug("OnceTaskDemo.doTask()");
	}

}
