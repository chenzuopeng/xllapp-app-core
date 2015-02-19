package org.xllapp.app.core;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通过此定时任务实现了一种通过文件进行JVM进程内外通信的机制.
 * 
 * @author dylan.chen Jun 13, 2014
 * 
 */
public class SignalWatcher extends ScheduledTask {

	private final static Logger logger = LoggerFactory.getLogger(SignalWatcher.class);

	private enum SIGNAL {

		NONE, INVALID, EXIT;

		public static SIGNAL getSignal(String str) {
			try {
				return SIGNAL.valueOf(str);
			} catch (Throwable throwable) {
				return SIGNAL.INVALID;
			}
		}
	}

	@Override
	public long getDelay() {
		return 10000;
	}

	@Override
	public void doTask() {

		SIGNAL signal = receiveSignal();

		logger.debug("received signal:{}", signal);

		switch (signal) {
		case EXIT:
			logger.info("handling {} signal", signal);
			System.exit(0);
			break;
		default:
			break;
		}
	}

	private SIGNAL receiveSignal() {

		File signalFile = new File(System.getProperty("user.dir"), "signal");

		try {

			if (!signalFile.exists()) {
				return SIGNAL.NONE;
			}
			String signalStr = readFile(signalFile);

			if (logger.isDebugEnabled()) {
				logger.debug("read raw[{}] from signal file[{}]", signalStr, signalFile.getAbsolutePath());
			}

			return SIGNAL.getSignal(signalStr);

		} catch (Throwable throwable) {
			logger.warn("failure to read signal file.caused by:" + throwable.getLocalizedMessage(), throwable);
		} finally {
			FileUtils.deleteQuietly(signalFile);
		}

		return SIGNAL.NONE;
	}

	private String readFile(File file) throws IOException {
		List<String> list = FileUtils.readLines(file, "UTF-8");
		return list.isEmpty() ? "" : list.get(0);
	}

}
