package com.lostcode.javalib.entities.components.generic;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.components.ComponentManager;
import com.lostcode.javalib.entities.components.physical.Sensor;

/**
 * Component wrapper for a sensor fixture that triggers an event in the
 * EntityWorld when entities pass through it.
 * 
 * @author Natman64
 * @created Aug 1, 2013
 * 
 */
public class TriggerZone extends Sensor {

	private FixtureDef fixtureDef;

	/**
	 * Constructs a TriggerZone.
	 * 
	 * @param owner
	 *            The Entity that owns this TriggerZone.
	 * @param shape
	 *            The shape of the sensor fixture.
	 */
	public TriggerZone(Entity owner, Shape shape) {
		super(owner);

		fixtureDef = new FixtureDef();
		fixtureDef.isSensor = true;
		fixtureDef.shape = shape;
	}

	/**
	 * Constructs a rectangle TriggerZone.
	 * 
	 * @param owner
	 *            The Entity that owns this TriggerZone.
	 * @param width
	 *            The width of the sensor fixture.
	 * @param height
	 *            The height of the sensor fixture.
	 */
	public TriggerZone(Entity owner, float width, float height) {
		super(owner);

		fixtureDef = new FixtureDef();
		fixtureDef.isSensor = true;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2, height / 2);

		fixtureDef.shape = shape;
	}

	@Override
	public void onRemove(ComponentManager container) {
		super.onRemove(container);

		fixtureDef.shape.dispose();
	}

	@Override
	public void refresh() {
		super.refresh();

		fixtureDef.isSensor = true;

		fixture = body.createFixture(fixtureDef);
		fixture.setUserData(owner);
	}

}
