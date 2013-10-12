package com.punchline.javalib.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.punchline.javalib.entities.systems.EntitySystem;

/**
 * Manages all of the {@link EntityWorld}'s systems.
 * 
 * @author Natman64
 * 
 */
public class SystemManager implements Disposable {

	private EntityWorld world;
	private List<EntitySystem> systems = new ArrayList<EntitySystem>();

	// region Initialization/Disposal

	/**
	 * Initializes the SystemManager
	 * 
	 * @param world
	 *            The world in which all systems are contained.
	 */
	public SystemManager(EntityWorld world) {
		this.world = world;
	}

	/**
	 * Disposes of all systems.
	 */
	@Override
	public void dispose() {
		for (int i = systems.size() - 1; i >= 0; i--) {
			systems.get(i).dispose();
			systems.remove(i);
		}
	}

	// endregion

	// region System Management

	/**
	 * Adds a system to the SystemManager. {@link Entity Entities} that have
	 * already been created will not be added to the system's processing list,
	 * so do not call addSystem() from anywhere except
	 * {@link EntityWorld#buildSystems}.
	 * 
	 * @param system
	 *            The system to be added.
	 */
	public EntitySystem addSystem(EntitySystem system) {
		system.setWorld(world);
		system.resume();

		// To allow for the addition of systems at runtime.
		// We must check all the entities in the world for the system.
		// This shouldn't take up too much processing,
		// because most systems are added on start up (0) entities.
		for (Entity e : world.entities.getEntities()) {
			if (system.canProcess(e))
				system.add(e);
		}

		systems.add(system);

		return system;
	}

	// endregion

	// region Events

	/**
	 * Pauses all systems.
	 */
	public void pause() {
		for (EntitySystem system : systems) {
			system.pause();
		}
	}

	/**
	 * Resumes all systems.
	 */
	public void resume() {
		for (EntitySystem system : systems) {
			system.resume();
		}
	}

	// endregion

	// region Processing

	/**
	 * Processes all incoming, outgoing, and modified {@link Entity Entities} to
	 * determine which systems should be notified. Then, runs all system
	 * processing.
	 * 
	 * @param newEntities
	 *            Incoming entities.
	 * @param changedEntities
	 *            Modified entities.
	 * @param removedEntities
	 *            Outgoing entities.
	 */
	public void process(Array<Entity> newEntities,
			Array<Entity> changedEntities, Array<Entity> removedEntities,
			float deltaSeconds) {

		for (EntitySystem system : systems) {

			// Processes all of new Entities.
			for (Entity e : newEntities) {
				if (system.canProcess(e) && !system.isProcessing(e)) {
					system.add(e); // The system can process this Entity, so add
									// it
				}
			}

			// Processes all of the change entities.
			for (Entity e : changedEntities) {
				if (system.isProcessing(e)) {
					if (!system.canProcess(e)) {
						system.remove(e);
					}
				} else {
					if (system.canProcess(e)) {
						system.add(e); // The system can process this Entity, so
										// add it
					}
				}

			}

			// Processes all of the removed entities
			for (Entity e : removedEntities) {
				if (system.isProcessing(e)) {
					system.remove(e); // The system was processing this Entity,
										// so remove it.
				}
			}

			if (system.getInterval() > 0) {
				system.addElapsedInterval(deltaSeconds);

				if (system.getElapsedInterval() >= system.getInterval()) {
					system.processEntities();
					system.resetElapsedInterval();
				}
			} else {
				system.processEntities();
			}

		}

	}

	// endregion

	// region System Performance

	/**
	 * @return A map of system names with their respective delta times, for
	 *         measuring performance.
	 */
	public Map<String, Float> systemPerformance() {
		Map<String, Float> performance = new HashMap<String, Float>();

		for (EntitySystem system : systems) {
			performance.put(system.getClass().getSimpleName(),
					system.processTime());
		}

		return performance;
	}

	// endregion

}
