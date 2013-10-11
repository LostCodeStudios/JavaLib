package com.punchline.javalib.entities.events.processes;

import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.events.EventCallback;
import com.punchline.javalib.entities.processes.*;
import com.punchline.javalib.entities.processes.Process;

/**
 * @author William
 * The callback for ending processes.
 */
public class EndProcessCallback implements EventCallback {
	

	/**
	 * Initializes the callback with a process to be ended.
	 * @param callbackProcess The internal process which will be ended once the callback is invoked
	 * @param endState The state with which the process will be ended 
	 */
	public EndProcessCallback(Process callbackProcess, ProcessState endState){
		this.callbackProcess = callbackProcess;
		this.endState = endState;
	}
	
	/** The internal process which will be ended once the callback is invoked.*/
	private Process callbackProcess;
	
	/** The state with which the process will be ended */
	private ProcessState endState;
	
	
	@Override
	public void invoke(Entity e, Object... args) {
		//End the process with a state.
		callbackProcess.end(endState);
		
	}

}
