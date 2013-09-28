package com.punchline.javalib.entities.processes;

import com.badlogic.gdx.utils.Array;
import com.punchline.javalib.entities.EntityWorld;


/**
 * A process object.
 * @author MadcowD
 */
public abstract class Process {
	
	
	//region Functioning Loop
	
	/**
	 * Function first called when the process is ran.
	 */
	public void start(){
		state = ProcessState.RUNNING;
	}
	
	/**
	 * Updates the process based on a certain delta time from the previous call. TO END A PROCESS CALL THIS.END()
	 * @param deltaTime The time in MS that the previous call was called.
	 */
	public abstract void update(EntityWorld world, float deltaTime);
	
	
	/**
	 * Called when a process is ended (after all updates have been called).
	 */
	public void end(ProcessState endState){
		if(endState.equals(ProcessState.RUNNING))
			return;
		else
			state = endState;
			
	}
	
	//endregion
	
	
	//region Mutators/Accessors
	/**
	 * Gets the state of the process.
	 * @return The current state of the process.
	 */
	public ProcessState getState(){
		return state;
	}
	
	
	
	//region Children
	/**
	 * Attaches a process to this process as a child to be called on the success of this process.
	 * @param child The child to be attached.
	 */
	public void attatchChild(Process child){
		childProcesses.add(child);
	}
	
	/**
	 * Removes a processes from the children of this process.
	 * @param child The child to be removed.
	 */
	public void detachChild(Process child){
		childProcesses.removeValue(child, true);
	}
	
	/**
	 * Gets a GDX array of the children processes.
	 * @return The children processes of this process.
	 */
	public Array<Process> getChildren(){
		return childProcesses;
	}
	
	//endregion
	
	//endregion
	
	//region Fields
	ProcessState state;
	Array<Process> childProcesses = new Array<Process>();
	
	
	//endregion
}
