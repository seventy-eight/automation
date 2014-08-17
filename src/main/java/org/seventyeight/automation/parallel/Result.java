package org.seventyeight.automation.parallel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Result {
	private static Logger logger = LogManager.getLogger(Result.class);

	public void process() {
		logger.debug("Default result implementation that does nothing");
	}
}
