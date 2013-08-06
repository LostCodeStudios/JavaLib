package com.punchline.javalib.entities.components.physical;

import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.Component;
import com.punchline.javalib.entities.components.ComponentManager;

/**
 * Component wrapper for a Box2D body that Represents an Entity's field of view. Must only be added to Entities
 * that already have {@link Body} components. Contains a callback for 
 * when another Entity is detected.
 * @author Natman64
 *
 */
public class Sensor implements Component {

	//region Callback
	
	/**
	 * Callback interface for sensor detection events.
	 * @author Nathaniel
	 *
	 */
	public interface SensorCallback {
		
		/**
		 * Called when a sensor detects an Entity, or an Entity escapes a sensor.
		 * @param e
		 */
		public void invoke(Entity e);
		
	}
	
	//endregion
	
	//region Fields
	
	/**
	 * The Entity that owns this component.
	 */
	protected Entity owner;
	
	/**
	 * The body this Sensor is attached to.
	 */
	protected com.badlogic.gdx.physics.box2d.Body body;
	
	/**
	 * The fixture representing this sensor.
	 */
	protected com.badlogic.gdx.physics.box2d.Fixture fixture;
	
	/**
	 * Callback for when this sensor detects an Entity.
	 */
	public SensorCallback onDetection;
	
	/**
	 * Called when an Entity leaves this sensor's view.
	 */
	public SensorCallback onEscape;
	
	//endregion
	
	//region Initialization
	
	/**
	 * Constructs a Sensor component.
	 * @param owner The Entity that contains this sensor. Must already have a {@link Body} component.
	 */
	public Sensor(Entity owner) {
		this.owner = owner;
	}
	
	//endregion
	
	//region Events
	
	@Override
	public void onAdd(ComponentManager container) {
		body = ((Body) container.getComponent(Body.class)).getBody();
		refresh();
	}

	@Override
	public void onRemove(ComponentManager container) { }

	//endregion
	
	//region Helpers
	
	/**
	 * Forces this Sensor to re-calculate its view bounds.
	 */
	public void refresh() { }
	
	//endregion
	
}
