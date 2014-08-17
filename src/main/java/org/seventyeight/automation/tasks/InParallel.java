package org.seventyeight.automation.tasks;

import java.util.concurrent.ExecutionException;

import org.seventyeight.automation.JsonConfiguration;
import org.seventyeight.automation.Orchestrator;
import org.seventyeight.automation.Task;
import org.seventyeight.automation.parallel.Result;

import com.google.gson.JsonObject;

public class InParallel extends Task {

	public InParallel(JsonObject json, JsonConfiguration parser) {
		super(json, parser);
	}

	protected Orchestrator orchestrator = new Orchestrator();
	
	public int getNumberOfThreads() {
		if(json.has("threads")) {
			return json.get("threads").getAsInt();
		} else {
			return 1;
		}
	}
	
	@Override
	public String getName() {
		return "parallel";
	}

	@Override
	public Result run() throws InterruptedException, ExecutionException {
		orchestrator.run(this, getNumberOfThreads());
		return null;
	}
	
	

}
