package org.seventyeight.automation;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonFlowTest {

	@Test
	public void test() throws IOException, AutomationException, InterruptedException, ExecutionException {
		JsonParser parser = new JsonParser();
		String json = readFile(getClass().getResource("flowtest.json").getFile(), Charset.defaultCharset());
		JsonElement j = parser.parse(json);
		JsonConfiguration c = new JsonConfiguration();
		List<Task> tasks = c.parse((JsonObject) j);
		
		for(Task task : tasks) {
			task.run();
		}

	}
	
	@Test
	public void test2() throws IOException, AutomationException, InterruptedException, ExecutionException {
		String file = getClass().getResource("flowtest.json").getFile();

		Core core = new Core();
		core.run(file);
	}
	
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
