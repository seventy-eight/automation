package org.seventyeight.automation;

import java.util.concurrent.ExecutionException;

import org.seventyeight.automation.parallel.Result;

import com.google.gson.JsonObject;

public abstract class Task {
	
	protected JsonObject json;
	protected JsonConfiguration parser;
	
	public Task(JsonObject json, JsonConfiguration parser) {
		this.json = json;
		this.parser = parser;
	}
	
	public abstract String getName();
	
	public abstract Result run() throws InterruptedException, ExecutionException, AutomationException;
	
	@Override
	public String toString() {
		return getName();
	}
}
