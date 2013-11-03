package com.lostcode.javalib.entities.systems;

import com.lostcode.javalib.entities.Entity;

/**
 * An {@link EntitySystem} that processes a group of {@link Entity Entities}.
 * 
 * @author Natman64
 * 
 */
public abstract class GroupSystem extends EntitySystem {

	private String group;

	/**
	 * Makes a new GroupSystem.
	 * 
	 * @param group
	 *            The name of the group for this system to track.
	 */
	public GroupSystem(String group) {
		this.group = group;
	}

	@Override
	public boolean canProcess(Entity e) {
		return e.getGroup().equals(group);
	}

}
