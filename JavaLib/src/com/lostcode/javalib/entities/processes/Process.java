package com.lostcode.javalib.entities.processes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.utils.Array;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.ProcessManager;
import com.lostcode.javalib.entities.components.generic.Health;
import com.lostcode.javalib.entities.events.EventCallback;
import com.lostcode.javalib.entities.events.processes.EndProcessCallback;

/**
 * A Process that is run repeatedly by the EntityWorld for a temporary amount of
 * time.
 * 
 * @author MadcowD
 */
public abstract class Process {

	// region Inner Classes
	
	/**
	 * An event that can occur to an Entity
	 * @author Natman64
	 * @created  Oct 27, 2013
	 */
	public final class EntityEvent {
		
		/** Bitwise flag for deletion events */
		public static final int DELETION = 1;
		
		/** Bitwise flag for death events */
		public static final int DEATH = 2;
		
	}
	
	// endregion
	
	// region Fields

	private ProcessState state;
	private Array<Process> childProcesses = new Array<Process>();
	
	private Map<Entity, Integer> linkedEntities = new HashMap<Entity, Integer>();
	
	// endregion

	// region Mutators/Accessors

	/**
	 * Gets the state of the process.
	 * 
	 * @return The current state of the process.
	 */
	public ProcessState getState() {
		return state;
	}

	/**
	 * Gets a GDX array of the children processes.
	 * 
	 * @return The children processes of this process.
	 */
	public Array<Process> getChildren() {
		return childProcesses;
	}

	// endregion

	// region Children

	/**
	 * Attaches a process to this process as a child to be called on the success
	 * of this process.
	 * 
	 * @param child
	 *            The child to be attached.
	 */
	public void attachChild(Process child) {
		childProcesses.add(child);
	}

	/**
	 * Removes a processes from the children of this process.
	 * 
	 * @param child
	 *            The child to be removed.
	 */
	public void detachChild(Process child) {
		childProcesses.removeValue(child, true);
	}

	// endregion

	// region Life Cycle

	/**
	 * Function first called when the process is run for the first time.
	 */
	public void start() {
		state = ProcessState.RUNNING;
	}

	/**
	 * Updates the process.
	 * 
	 * @param world
	 * @param deltaTime
	 *            The time in seconds since the previous call of update().
	 */
	public abstract void update(EntityWorld world, float deltaTime);

	/**
	 * Called when a process is ended (after all updates have been called).
	 */
	public void end(ProcessState endState) {
		if (endState.equals(ProcessState.RUNNING)) {
			return;
		} else {
			state = endState;
			
			unlinkEntities();
		}
	}

	// endregion

	// region Entity Linking
	
	/**
	 * Links an Entity to this process.
	 * @param e The Entity to link.
	 * @param eventFlags The flags of the events to subscribe to. These flags
	 * are contained in {@link EntityEvent} and are encoded in bitwise.
	 * @param eventCallback The callback to be invoked when the entity's event
	 * takes places
	 */
	protected void link(Entity e, Integer eventFlags, EventCallback eventCallback) {
		linkedEntities.put(e, eventFlags);
		
		if ((eventFlags & EntityEvent.DELETION) == EntityEvent.DELETION) {
			e.onDeleted.addCallback(this, eventCallback);
		}
		
		if ((eventFlags & EntityEvent.DEATH) == EntityEvent.DEATH) {
			Health h = e.getComponent(Health.class);
			
			h.onDeath.addCallback(this, eventCallback);
		}
	}
	
	/**
	 * Links an Entity to this Process.
	 * @param e The entity to link.
	 * @param eventFlags The events of the entity that will trigger this process to end
	 * @param endState The state to end on.
	 */
	protected void link(Entity e, Integer eventFlags, ProcessState endState) {
		this.link(e, eventFlags, new EndProcessCallback(this, endState));
	}
	
	/**
	 * Unlinks an Entity from this Process
	 * @param e
	 */
	protected void unlink(Entity e) {
		Integer eventFlags = linkedEntities.get(e);
		
		linkedEntities.remove(e);
		
		if ((eventFlags & EntityEvent.DELETION) == EntityEvent.DELETION) {
			e.onDeleted.removeCallback(this);
		}
		
		if ((eventFlags & EntityEvent.DEATH) == EntityEvent.DEATH) {
			Health h = e.getComponent(Health.class);
			
			h.onDeath.removeCallback(this);
		}
	}
	
	/**
	 * Unlinks all linked entities.
	 */
	protected void unlinkEntities() {
		Iterator<Entry<Entity, Integer>> it = linkedEntities.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Entity, Integer> entry = it.next();
			Entity e = entry.getKey();
			Integer flag = entry.getValue();
			
			if ((flag & EntityEvent.DELETION) == EntityEvent.DELETION) {
				e.onDeleted.removeCallback(this);
			}
			
			if ((flag & EntityEvent.DEATH) == EntityEvent.DEATH) {
				Health h = e.getComponent(Health.class);
				
				h.onDeath.removeCallback(this);
			}
		}
		
		linkedEntities.clear();
	}
	
	// endregion
	
	// region Events

	/**
	 * Event called when this process is ended by the {@link ProcessManager}.
	 * 
	 * @param world
	 * @param endState
	 *            The state this Process ended with.
	 */
	public abstract void onEnd(EntityWorld world, ProcessState endState);

	// endregion

}
