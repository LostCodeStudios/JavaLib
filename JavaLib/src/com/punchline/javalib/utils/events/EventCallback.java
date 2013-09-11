package com.punchline.javalib.utils.events;

import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;

/**
 * Interface for any event callback. EventCallbacks must subscribe to an {@link EventHandler} that will invoke them
 * when necessary.
 * @author Natman64
 *
 */
public interface EventCallback {

	/**
	 * Called when the event that this callback subscribed to is invoked.
	 * @param world The main EntityWorld.
	 * @param e The Entity that triggered this event.
	 * @param args Miscellaneous arguments for the event.
	 */
	public void invoke(EntityWorld world, Entity e, Object... args);
	
}
