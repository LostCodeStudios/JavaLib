package com.punchline.javalib.entities.templates;

import java.util.ArrayList;

import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;

/**
 * Base class for group templates. A group template will create multiple entities at the same time.
 * @author Natman64
 *
 */
public interface EntityGroupTemplate {

	/**
	 * Creates a group of entities.
	 * @param world The EntityWorld for the entities to inhabit.
	 * @param args Arguments for the creation of the group.
	 * @return The entity group.
	 */
	public ArrayList<Entity> buildEntities(EntityWorld world, Object... args);
	
}
