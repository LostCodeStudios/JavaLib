package com.lostcode.javalib.entities.systems;

import com.lostcode.javalib.entities.Entity;

/**
 * An {@link EntitySystem} that processes a single {@link Entity} by its tag.
 * 
 * @author Natman64
 * 
 */
public abstract class TagSystem extends EntitySystem {

	private String tag;

	/**
	 * Makes a TagSystem.
	 * 
	 * @param tag
	 *            The tag for the system to track.
	 */
	public TagSystem(String tag) {
		this.tag = tag;
	}

	@Override
	public boolean canProcess(Entity e) {
		return e.getTag().equals(tag);
	}

}
