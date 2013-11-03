package com.lostcode.javalib.entities.templates;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;

/**
 * Base class for group templates. A group template will create multiple
 * entities at the same time.
 * 
 * @author Natman64
 * 
 */
public interface EntityGroupTemplate extends Disposable {

	/**
	 * Creates a group of entities.
	 * 
	 * @param world
	 *            The EntityWorld for the entities to inhabit.
	 * @param args
	 *            Arguments for the creation of the group.
	 * @return The entity group.
	 */
	public Array<Entity> buildEntities(EntityWorld world, Object... args);

}
