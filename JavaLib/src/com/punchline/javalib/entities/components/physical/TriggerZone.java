package com.punchline.javalib.entities.components.physical;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.punchline.javalib.entities.Entity;

/**
 * Component wrapper for a sensor fixture that triggers an event in the EntityWorld.
 * @author Natman64
 * @created Aug 1, 2013
 * 
 */
public class TriggerZone extends Sensor {

	/**
	 * Constructs a TriggerZone.
	 * @param owner The Entity that owns this TriggerZone.
	 */
	public TriggerZone(Entity owner, BodyDef bodyDef, FixtureDef fixtureDef) {
		super(owner);
	}
	
}
