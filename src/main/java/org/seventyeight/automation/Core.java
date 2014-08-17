package org.seventyeight.automation;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Core {

	private Orchestrator o = new Orchestrator();
	
	public void run(JsonObject json) throws AutomationException, InterruptedException, ExecutionException {
		run_(json);
	}
	
	public void run(String file) throws IOException, AutomationException, InterruptedException, ExecutionException {
		String json = Utils.readFile(file, Charset.defaultCharset());
		
		JsonParser parser = new JsonParser();
		JsonElement j = parser.parse(json);
		
		run_((JsonObject) j);
	}
	
	private void run_(JsonObject json) throws AutomationException, InterruptedException, ExecutionException {
		JsonConfiguration c = new JsonConfiguration();
		List<Task> tasks = c.parse(json);
		
		for(Task task : tasks) {
			o.run(task, 1);
		}
	}
}
