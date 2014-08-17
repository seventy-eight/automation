package org.seventyeight.automation;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.seventyeight.automation.parallel.Result;
import org.seventyeight.automation.tasks.Display;

import com.google.gson.JsonObject;

public class OrchestratorTest {

	@Test
	public void ExecutionExceptionTest() throws InterruptedException, ExecutionException {
		Orchestrator o = new Orchestrator();
		o.run(new TestTask(null), 1);
		
		JsonObject json = new JsonObject();
		json.addProperty("text", "WHOOP WHOOP!");
		o.run(new Display(json, null), 2);
	}

	
	protected class TestTask extends Task {

		public TestTask(JsonObject json) {
			super(json, null);
		}

		@Override
		public String getName() {
			return "test";
		}

		@Override
		public Result run() throws InterruptedException, ExecutionException {
			// TODO Auto-generated method stub
			return new Result();
		}
		
	}
}
