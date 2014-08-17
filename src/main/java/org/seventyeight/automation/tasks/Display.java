package org.seventyeight.automation.tasks;

import java.util.concurrent.ExecutionException;

import org.seventyeight.automation.JsonConfiguration;
import org.seventyeight.automation.Task;
import org.seventyeight.automation.TaskItem;
import org.seventyeight.automation.parallel.Result;

import com.google.gson.JsonObject;

@TaskItem(name = "display")
public class Display extends Task {

	public Display(JsonObject json, JsonConfiguration parser) {
		super(json, parser);
	}

	@Override
	public String getName() {
		return "display";
	}

	@Override
	public Result run() throws InterruptedException, ExecutionException {
		if(json.has("text")) {
			System.out.println("Displaying: " + json.get("text").getAsString());
		} else {
			System.out.println("Nothing to display");
		}
		
		return new Result();
	}

	
}
