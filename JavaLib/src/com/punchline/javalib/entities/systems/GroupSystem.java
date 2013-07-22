package com.punchline.javalib.entities.systems;

import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntitySystem;

/**
 * An {@link EntitySystem} that processes a group of {@link Entity Entities}.
 * @author Nathaniel
 *
 */
public abstract class GroupSystem extends EntitySystem {

	String group;
	
	/**
	 * Makes a new GroupSystem.
	 * @param group The name of the group for this system to track.
	 */
	public GroupSystem(String group) {
		this.group = group;
	}
	
	@Override
	public boolean canProcess(Entity e) {
		return e.getGroup().equals(group);
	}

}
