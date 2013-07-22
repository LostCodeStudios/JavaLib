package com.punchline.javalib.entities.systems;

import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntitySystem;

/**
 * An {@link EntitySystem} that processes a single type of {@link Entity}.
 * @author Nathaniel
 *
 */
public abstract class TypeSystem extends EntitySystem {

	String type;
	
	/**
	 * Makes a new TypeSystem.
	 * @param type The type this system will process.
	 */
	public TypeSystem(String type) {
		this.type = type;
	}
	
	@Override
	public boolean canProcess(Entity e) {
		return e.getType().equals(type);
	}

}
