package org.seventyeight.automation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.seventyeight.automation.parallel.Result;

public class Orchestrator {
	
	private static Logger logger = LogManager.getLogger(Orchestrator.class);

	protected ExecutorService pool;
	
	public Orchestrator() {
		pool = Executors.newCachedThreadPool();
	}
	
	public void run(Task task, int threads) throws InterruptedException, ExecutionException {
		Set<Callable<Result>> callables = new HashSet<Callable<Result>>();
		for(int i = 0 ; i < threads ; i++) {
			callables.add(new RunnableTask(task));
		}

		List<Future<Result>> futures = pool.invokeAll(callables);

		for(Future<Result> result : futures){
		    // Do something with the result!?
			result.get().process();
		}
	}
	
	public static class RunnableTask implements Callable<Result> {

		protected Task task;
		
		public RunnableTask(Task task) {
			this.task = task;
		}

		@Override
		public Result call() throws Exception {
			logger.debug("Executing {}, thread {}", task, Thread.currentThread().getName());
			return task.run();
		}
		
	}
}
