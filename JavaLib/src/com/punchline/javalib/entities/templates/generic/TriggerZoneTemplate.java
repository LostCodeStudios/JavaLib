package com.punchline.javalib.entities.templates.generic;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.generic.TriggerZone;
import com.punchline.javalib.entities.templates.EntityTemplate;

public class TriggerZoneTemplate implements EntityTemplate {

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		
		BodyDef bd = (BodyDef) args[0];
		FixtureDef fd = (FixtureDef) args[1];
		MapProperties properties = (MapProperties) args[2];
		
		e.init("", "Zones", "TriggerZone");
		
		TriggerZone zone = new TriggerZone(e, fd);
		

		
		return e;
	}

}
