package com.punchline.javalib.entities.components.generic;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.physical.Sensor;

/**
 * Component wrapper for a sensor fixture that triggers an event in the EntityWorld.
 * @author Natman64
 * @created Aug 1, 2013
 * 
 */
public class TriggerZone extends Sensor {
	
	private FixtureDef fixtureDef;
	
	/**
	 * Constructs a TriggerZone.
	 * @param owner The Entity that owns this TriggerZone.
	 * @param fixtureDef Definition of the zone's world fixture.
	 */
	public TriggerZone(Entity owner, FixtureDef fixtureDef) {
		super(owner);
		
		this.fixtureDef = fixtureDef;
	}

	@Override
	public void refresh() {
		super.refresh();
		
		fixtureDef.isSensor = true;
		
		fixture = body.createFixture(fixtureDef);
		fixture.setUserData(owner);
	}
	
}
