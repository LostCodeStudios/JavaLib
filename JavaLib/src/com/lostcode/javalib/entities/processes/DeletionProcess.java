package com.lostcode.javalib.entities.processes;

import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;

/**
 * Deletes an Entity. Meant to be timed with a {@link DelayProcess}.
 * @author Natman64
 * @created Oct 13, 2013
 */
public class DeletionProcess extends Process {
	
	private Entity e;
	
	/**
	 * Creates a DeletionProcess.
	 * @param e
	 */
	public DeletionProcess(Entity e) {
		this.e = e;
	}
	
	@Override
	public void update(EntityWorld world, float deltaTime) {
		e.delete(); //delete the entity
		end(ProcessState.SUCCEEDED); //end
	}

	@Override
	public void onEnd(EntityWorld world, ProcessState endState) {
		
	}

}
