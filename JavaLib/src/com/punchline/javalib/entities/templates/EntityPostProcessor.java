package com.punchline.javalib.entities.templates;

import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;

/**
 * Interface for classes that need to perform processing on Entities immediately after they are created.
 * @author Natman64
 *
 */
public interface EntityPostProcessor {

	/**
	 * Post processing on newly created Entities.
	 */
	public void process(EntityWorld world, Entity e);
	
}
