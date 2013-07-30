package com.punchline.javalib.entities.components.generic;

import java.util.ArrayList;
import java.util.List;

import com.punchline.javalib.entities.Entity;
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
			}
			
		};
		
		onEscape = new SensorCallback() {

			@Override
			public void invoke(Entity e) {
				entitiesInView.remove(e);
			}
			
		};
	}

	/**
	 * @return A list of all Entities that are currently in view.
	 */
	public List<Entity> getEntitiesInView() {
		return entitiesInView;
	}
	
}
