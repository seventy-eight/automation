package org.seventyeight.automation;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonConfiguration {
	
	private Map<String, Class<? extends Task>> tasks = new HashMap<String, Class<? extends Task>>();
	
	public JsonConfiguration() {
		Reflections reflections = new Reflections("org.seventyeight");
		
		Set<Class<?>> taskClasses = reflections.getTypesAnnotatedWith(TaskItem.class);
		for(Class<?> taskClass : taskClasses) {
			TaskItem taskItem = taskClass.getAnnotation(TaskItem.class);
			tasks.put(taskItem.name(), (Class<? extends Task>) taskClass);
		}
	}

	public List<Task> parse(JsonObject json) throws AutomationException {
		
		JsonElement tasks = json.get("tasks");
		return getSteps(tasks);
		
		//return null;
	}
	
	public List<Task> getSteps(JsonElement json) throws AutomationException {
		if(json.isJsonArray()) {
			return getSteps((JsonArray)json);
		} else {
			return new ArrayList<Task>();
		}
	}
	
	public List<Task> getSteps(JsonArray jsonArray) throws AutomationException {
		List<Task> steps = new ArrayList<Task>();
		for(JsonElement task : jsonArray) {
			if(task.isJsonObject() && task.getAsJsonObject().has("name")) {
				steps.add(getStep((JsonObject) task));
			}
		}
		
		return steps;
	}
	
	public Task getStep(JsonObject task) throws AutomationException {
		String name = task.get("name").getAsString();
		
		if(!tasks.containsKey(name)) {
			throw new IllegalStateException("No such task, " + name);
		}
		
		Class<? extends Task> clazz = tasks.get(name);
		try {
			Constructor<Task> constructor = (Constructor<Task>) clazz.getConstructor(JsonObject.class, JsonConfiguration.class);
			Task instance = constructor.newInstance(task, this);
			return instance;
		} catch(Exception e) {
			throw new AutomationException(e);
		}
	}
}
