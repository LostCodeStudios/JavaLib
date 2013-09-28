package com.punchline.javalib.entities;

import com.badlogic.gdx.utils.Array;
import com.punchline.javalib.entities.processes.ProcessState;

/**
 * Manages all processes in a given EntityWorld, W.
 * @author MadcowD
 * @created Sep 28, 2013
 */
public class ProcessManager {	
	//region Functioning Loop
	
	/**
	 * Processes all processes.
	 * @param deltaMS
	 */
	public void process(int deltaMS){

	}
	
	//endregion

	
	
	//region Mutators/Accessors
	/**
	 * Attaches a process to the process manager.
	 * @param process
	 */
	public void attach(com.punchline.javalib.entities.processes.Process process){
		attachedProcesses.add(process);
	}
	
	
	/**
	 * Aborts all of the processes in the Process manager.
	 */
	public void abortAll(){
		for(com.punchline.javalib.entities.processes.Process p : attachedProcesses){
			p.end(ProcessState.ABORTED);
		}
	}
	//endregion
	
	
	
	//region Fields
	
	Array<com.punchline.javalib.entities.processes.Process> attachedProcesses = new Array<com.punchline.javalib.entities.processes.Process>();
	
	//endregion
}
