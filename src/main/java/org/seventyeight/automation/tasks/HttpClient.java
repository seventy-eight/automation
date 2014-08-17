package org.seventyeight.automation.tasks;

import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.seventyeight.automation.AutomationException;
import org.seventyeight.automation.JsonConfiguration;
import org.seventyeight.automation.Task;
import org.seventyeight.automation.TaskItem;
import org.seventyeight.automation.parallel.Result;

import com.google.gson.JsonObject;

@TaskItem(name = "url")
public class HttpClient extends Task {
	
	private static Logger logger = LogManager.getLogger(HttpClient.class);

	public HttpClient(JsonObject json, JsonConfiguration parser) {
		super(json, parser);
	}
	
	public String getUrl() {
		if(json.has("url")) {
			return json.get("url").getAsString();
		} else {
			throw new IllegalStateException("Configuration does not have a url defined");
		}
	}

	@Override
	public String getName() {
		return "url";
	}

	@Override
	public Result run() throws InterruptedException, ExecutionException, AutomationException {
		logger.debug("YEAH");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(getUrl());
		
		try {
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			
			try {
			    System.out.println(response1.getStatusLine());
			    HttpEntity entity1 = response1.getEntity();
			    // do something useful with the response body
			    // and ensure it is fully consumed
			    EntityUtils.consume(entity1);
			} finally {
			    response1.close();
			}
		} catch(Exception e) {
			throw new AutomationException(e);
		}
		
		return new Result();
	}

}
