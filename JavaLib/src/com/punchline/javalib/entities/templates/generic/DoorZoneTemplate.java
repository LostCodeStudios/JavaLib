package com.punchline.javalib.entities.templates.generic;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.generic.TriggerZone;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.templates.EntityTemplate;

public class DoorZoneTemplate implements EntityTemplate {

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		
		BodyDef bd = (BodyDef) args[0];
		FixtureDef fd = (FixtureDef) args[1];
		MapProperties properties = (MapProperties) args[2];
		
		e.init("", "Zones", "DoorZone");
		
		fd.isSensor = true;
		
		final String door = (String) properties.get("Object");
		final String tiles = (String) properties.get("Tiles");
		
		final String[] coords = tiles.split(", ");
		
		TriggerZone zone = new TriggerZone(e, fd) {

			Cell tile;
			
			@Override
			public void onDetected(Entity e, EntityWorld world) {
				super.onDetected(e, world);
				
				
				if (e.getType().equals("Player")) {
					world.getMap().disableObject(door);
					
					for (int i = 0; i < coords.length; i++) {
						int x = Integer.parseInt(coords[i++]);
						int y = Integer.parseInt(coords[i]);
						
						tile = world.getMap().getTile("Objects", x, y);
						world.getMap().setTile("Objects", x, y, null);
					}
					
				}
			}

			@Override
			public void onEscaped(Entity e, EntityWorld world) {
				super.onEscaped(e, world);
				
				if (e.getType().equals("Player")) {
					world.getMap().enableObject(door);
					
					for (int i = 0; i < coords.length; i++) {
						int x = Integer.parseInt(coords[i++]);
						int y = Integer.parseInt(coords[i]);
						
						world.getMap().setTile("Objects", x, y, tile);
					}
					
				}
			}
			
		};
		
		e.addComponent(new Body(world, e, bd, fd));
		
		e.addComponent(zone);
		
		return e;
	}

}
