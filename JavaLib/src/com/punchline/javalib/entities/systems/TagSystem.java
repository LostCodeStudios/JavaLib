package com.punchline.javalib.entities.systems;

import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntitySystem;

/**
 * An {@link EntitySystem} that processes a single {@link Entity} by its tag.
 * @author Nathaniel
 *
 */
public abstract class TagSystem extends EntitySystem {

	String tag;
	
	/**
	 * Makes a TagSystem.
	 * @param tag The tag for the system to track.
	 */
	public TagSystem(String tag) {
		this.tag = tag;
	}
	
	@Override
	public boolean canProcess(Entity e) {
		return e.getTag().equals(tag);
	}

}
