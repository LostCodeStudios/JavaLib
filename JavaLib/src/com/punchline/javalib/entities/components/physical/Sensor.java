package com.punchline.javalib.entities.components.physical;

import java.util.ArrayList;
import java.util.List;

import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.Entity.EntityDeletionCallback;
import com.punchline.javalib.entities.components.Component;
import com.punchline.javalib.entities.components.ComponentManager;

/**
 * Component wrapper for a Box2D sensor fixture. Must only be added to Entities
 * that already have {@link Body} components. Contains event handlers for when Entities are detected and lost.
 * @author Natman64
 *
 */
public class Sensor implements Component {
	
	//region Fields
	
	private List<Entity> entitiesInView = new ArrayList<Entity>();
	
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
	
	//region Accessors
	
	/**
	 * @return A list of all entities inside this sensor.
	 */
	public List<Entity> getEntitiesInView() {
		return entitiesInView;
	}
	
	//endregion
	
	//region Events
	
	/**
	 * Called when an Entity enters the sensor.
	 * @param e The Entity that entered the sensor.
	 * @param world The world the Entity resides in.
	 */
	public void onDetected(Entity e, final EntityWorld world) {
		entitiesInView.add(e);
		
		e.onDeleted = new EntityDeletionCallback() {

			@Override
			public void invoke(Entity owner) {
				onEscaped(owner, world);
			}
			
		};
	}
	
	/**
	 * Called when an Entity escapes the sensor.
	 * @param e The Entity that escaped.
	 * @param world The world the Entity resides in.
	 */
	public void onEscaped(Entity e, final EntityWorld world) {
		entitiesInView.remove(e);
		
		e.onDeleted = null;
	}
	
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
	 * Forces this Sensor to re-calculate its fixture.
	 */
	public void refresh() { 
		if (fixture != null) body.destroyFixture(fixture); //Destroy the old fixture, if there is one
	}
	
	//endregion
	
}
