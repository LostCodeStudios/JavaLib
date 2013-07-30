package com.punchline.javalib.entities.components.generic;

import java.util.ArrayList;
import java.util.List;

import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.Entity.EntityDeletionCallback;
import com.punchline.javalib.entities.components.physical.Sensor;

/**
 * Generic Sensor component that tracks a list of entities that are currently within its view.
 * @author Nathaniel
 *
 */
public class View extends Sensor {

	private List<Entity> entitiesInView = new ArrayList<Entity>();
	
	/**
	 * Constructs a View.
	 * @param owner The owner of this View.
	 * @param viewRange The range of this View.
	 * @param fov The field of view.
	 */
	public View(Entity owner, float viewRange, float fov) {
		super(owner, viewRange, fov);
		
		onDetection = new SensorCallback() {

			@Override
			public void invoke(Entity e) {
				entitiesInView.add(e);
				
				e.onDeleted = new EntityDeletionCallback() {

					@Override
					public void invoke(Entity owner) {
						onEscape.invoke(owner);
					}
					
				};
			}
			
		};
		
		onEscape = new SensorCallback() {

			@Override
			public void invoke(Entity e) {
				entitiesInView.remove(e);
				
				e.onDeleted = null;
			}
			
		};
	}

	/**
	 * @return A list of all Entities that are currently in view.
	 */
	public List<Entity> getEntitiesInView() {
		return entitiesInView;
	}
	
	/**
	 * Processes the list of Entities in view, and removes nullified references.
	 */
	public void removeDeadEntities() {
		
		for (int i = entitiesInView.size(); i >= 0; i--) {
			Entity e = entitiesInView.get(i);
			if (e.hasComponent(Health.class)) {
				
					entitiesInView.remove(i); //Remove the reference if the entity died
			}
		}
		
	}
	
}
