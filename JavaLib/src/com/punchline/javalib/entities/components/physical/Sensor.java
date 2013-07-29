package com.punchline.javalib.entities.components.physical;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.Component;
import com.punchline.javalib.entities.components.ComponentManager;

/**
 * Component wrapper for a Box2D body that Represents an Entity's field of view. Must only be added to Entities
 * that already have {@link Body} components. Contains a callback for 
 * when another Entity is detected.
 * @author Nathaniel
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
	
	private Entity owner;
	
	private com.badlogic.gdx.physics.box2d.Body body;
	private com.badlogic.gdx.physics.box2d.Fixture fixture;
	
	private float viewRange;
	private float fov;
	private int vertices;
	
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
	 * @param viewRange The range that this Sensor can see.
	 * @param fov The field of view for this sensor, from 0 (seeing nothing) to 1 (seeing 360 degrees).
	 * @param vertices The number of vertices needed in this sensor. There must be at least 3, and any more
	 * may cause excess lag.
	 */
	public Sensor(Entity owner, float viewRange, float fov, int vertices) {
		//Clamp values to accepted ranges.
		
		this.owner = owner;
		this.viewRange = viewRange;
		this.setFOV(fov);
		this.setVertices(vertices);
	}
	
	/**
	 * Constructs a Sensor component.
	 * @param owner The Entity that contains this sensor. Must already have a {@link Body} component.
	 * @param viewRange The range that this Sensor can see.
	 * @param fov The field of view for this sensor, from 0 (seeing nothing) to 1 (seeing 360 degrees).
	 */
	public Sensor(Entity owner, float viewRange, float fov) {
		this(owner, viewRange, fov, 8);
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
	
	//region Accessors
	
	/**
	 * @return The range of this Sensor's view, in meters.
	 */
	public float getViewRange() {
		return viewRange;
	}
	
	/**
	 * @return This Sensor's field of view, from 0 to 1.
	 */
	public float getFOV() {
		return fov;
	}
	
	/**
	 * @return The number of vertices in the fixture representing this sensor.
	 */
	public int getVertices() {
		return vertices;
	}
	
	//endregion
	
	//region Mutators
	
	/**
	 * Sets the range of this sensor's view, in meters.
	 * @param viewRange The new view range.
	 */
	public void setViewRange(float viewRange) {
		this.viewRange = viewRange;
	}
	
	/**
	 * Sets this sensor's field of view, from 0 to 1.
	 * @param fov The new field of view.
	 */
	public void setFOV(float fov) {
		if (fov < 0) fov = 0;
		if (fov > 1) fov = 1;
		this.fov = fov;
	}
	
	/**
	 * Sets the number of vertices in the fixture representing this sensor.
	 * @param vertices The new amount of vertices.
	 */
	public void setVertices(int vertices) {
		if (vertices < 3) vertices = 3;
		if (vertices > 8) vertices = 8;
		this.vertices = vertices;
	}
	
	//endregion
	
	//region Helpers
	
	/**
	 * Forces this Sensor to re-calculate its view bounds.
	 */
	public void refresh() {
		Vector2[] verts = new Vector2[vertices];
		verts[0] = new Vector2(0, 0);
		
		float degrees = 360 * fov;
		
		float start = (float)Math.toRadians(degrees / 2);
		
		for (int i = 1; i < vertices; i++) {
			float angle = start - ((float)i / (vertices - 1)) * (float)Math.toRadians(degrees);
			
			verts[i] = new Vector2(viewRange * (float)Math.cos(angle), viewRange * (float)Math.sin(angle));
		}
		
		PolygonShape shape = new PolygonShape();
		shape.set(verts);
		
		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.isSensor = true;
		
		fixture = body.createFixture(fd);
		fixture.setUserData(owner);
	}
	
	//endregion
	
}
