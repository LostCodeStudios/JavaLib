package com.punchline.javalib.entities.processes;

import com.punchline.javalib.entities.Entity;

/**
 * Deletes an Entity after a specified amount of time ends.
 * @author Natman64
 * @created Oct 13, 2013
 */
public class ExpirationProcess extends DelayProcess {

	/**
	 * Creates an ExpirationProcess.
	 * @param time The amount of seconds to wait before deleting
	 * @param e The entity to delete
	 */
	public ExpirationProcess(float time, Entity e) {
		super(time, new DeletionProcess(e));
	}

}
