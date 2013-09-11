package com.punchline.javalib.utils.events;

import com.badlogic.gdx.utils.Array;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;

/**
 * Handles multiple callbacks that subscribe to a certain event, and invokes each of them when the event occurs.
 * @author Natman64
 *
 */
public class EventHandler {
	
	private Array<EventCallback> callbacks = new Array<EventCallback>();
	
	/**
	 * Subscribes a callback to this handler's event.
	 * @param callback The callback to add.
	 */
	public void addCallback(EventCallback callback) {
		callbacks.add(callback);
	}
	
	/**
	 * Unsubscribes a callback from this handler's event. 
	 * @param callback The callback to remove.
	 */
	public void removeCallback(EventCallback callback) {
		callbacks.removeValue(callback, true);
	}
	
	/**
	 * Invokes every {@link EventCallback} that is subscribed to this handler's event.
	 * @param world The main EntityWorld.
	 * @param e The Entity that triggered this handler's event.
	 * @param args Miscellaneous arguments for the event.
	 */
	public void invoke(EntityWorld world, Entity e, Object... args) {
		for (EventCallback callback : callbacks) {
			callback.invoke(world, e, args);
		}
	}
	
}
