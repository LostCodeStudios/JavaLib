package com.lostcode.javalib.entities.tiles;

import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.templates.EntityTemplate;

/**
 * Template for making a TileMap entity. The args for creating a TileMap are as
 * follows: [0] String mapFilename - the file path of the map file [1] String
 * materialsFilename - the file path of this map materials XML file
 * 
 * @author Natman64
 * @created Aug 1, 2013
 */
public class TileMapTemplate implements EntityTemplate {

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		String mapFilename = (String) args[0];
		String materialsFilename = (String) args[1];

		e.init("", "Maps", "TileMap");

		TileMap map = new TileMap(world, mapFilename, materialsFilename);
		e.addComponent(map);

		return e;
	}

	@Override
	public void dispose() {
	}

}
