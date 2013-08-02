package com.punchline.javalib.entities.templates.tile;

import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.tile.TileMap;
import com.punchline.javalib.entities.templates.EntityTemplate;

/**
 * Template for making a TileMap entity. The args for creating a TileMap are as follows:
 * [0] String mapFilename - the file path of the map file
 * [1] String materialsFilename - the file path of this map materials XML file
 * @author Natman64
 * @created Aug 1, 2013
 */
public class TileMapTemplate implements EntityTemplate {

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		String mapFilename = (String)args[0];
		String materialsFilename = (String)args[1];
		
		e.init("Map", "", "TileMap");
		
		TileMap map = new TileMap(world, mapFilename, materialsFilename);
		e.addComponent(map);
		
		return e;
	}

}
