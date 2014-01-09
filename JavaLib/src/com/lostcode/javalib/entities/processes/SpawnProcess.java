package com.lostcode.javalib.entities.processes;

import com.lostcode.javalib.entities.EntityWorld;

/**
 * A {@link Process} that just spawns an Entity using the arguments given given
 * 
 * @author GenericCode
 * 
 */

public class SpawnProcess extends Process {

	private String template;
	private Object[] arguments;
	/**
	 * @param template
	 *            The template of the Entity to be created.
	 * @param args
	 *            The arguments needed to create the Entity.     
	 */
	public SpawnProcess( String template, Object... args ) {
		this.template = template;
		this.arguments = args;
	}
	@Override
	public void update(EntityWorld world, float deltaTime) {
		world.createEntity(template, arguments);
		end(ProcessState.SUCCEEDED);
	}

	@Override
	public void onEnd(EntityWorld world, ProcessState endState) {}

}
