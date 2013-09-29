package com.punchline.javalib.entities;

import java.util.Iterator;

import com.badlogic.gdx.utils.Array;
import com.punchline.javalib.entities.processes.Process;
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
	 * @param deltaTime 
	 * @param world 
	 */
	public void process(EntityWorld world, float deltaTime ){
		for(Iterator<com.punchline.javalib.entities.processes.Process> i
				= attachedProcesses.iterator(); i.hasNext(); )
		{
			//Get the process
			Process p = i.next();
			
			//Handle states
			if(p.getState() == ProcessState.RUNNING) //IF IT'S RUNNING, UPDATE
				p.update(world, deltaTime );
			
			else{ //IF IT IS DEAD
				 if (p.getState() == ProcessState.SUCCEEDED)
						for(Process child : p.getChildren())
							this.attach(child);
				 //REMOVE FROM PROCESS MANAGER
				 i.remove();
			}
		}
	}
	
	//endregion

	//region Mutators/Accessors
	/**
	 * Attaches a process to the process manager and STARTS it.
	 * @param process
	 */
	public void attach(com.punchline.javalib.entities.processes.Process process){
		attachedProcesses.add(process);
		process.start();
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
