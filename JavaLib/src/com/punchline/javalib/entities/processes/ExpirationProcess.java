package com.punchline.javalib.entities.processes;

import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.events.processes.EndProcessCallback;

/**
 * Deletes an Entity after a specified amount of time ends.
 * @author Natman64
 * @created Oct 13, 2013
 */
public class ExpirationProcess extends DelayProcess {

	private Entity e;
	
	/**
	 * Creates an ExpirationProcess.
	 * @param time The amount of seconds to wait before deleting
	 * @param e The entity to delete
	 */
	public ExpirationProcess(float time, Entity e) {
		super(time, new DeletionProcess(e));
		
		e.onDeleted.addCallback(this, new EndProcessCallback(this, ProcessState.FAILED));
		this.e = e;
	}

	@Override
	public void onEnd(EntityWorld world, ProcessState endState) {
		if (endState == ProcessState.ABORTED) {
			e.onDeleted.removeCallback(this);
		}
	}

}
