package com.punchline.javalib.entities;

import java.util.HashMap;
import java.util.Map;

public class EntityWorld extends ComponentManager {

	/**
	 * This world's {@link EntityManager}.
	 */
	EntityManager entities;
	
	/**
	 * Template map.
	 */
	Map<String, EntityTemplate> templates;
	
	/**
	 * Instantiates the EntityWorld's {@link EntityManager}, template map, and component map.
	 */
	public EntityWorld() {
		entities = new EntityManager();
		templates = new HashMap<String, EntityTemplate>(); //TODO: HashMap?
	}
	
	/**
	 * Creates an {@link Entity} using the {@link EntityTemplate} associated with the given tag.
	 * @param template The tag of the template.
	 * @param args Arguments for creating the {@link Entity}.
	 * @return The created entity.
	 */
	public Entity createEntity(String template, Object... args) {
		Entity e = templates.get(template).buildEntity(args);
		entities.add(e);
		return e;
	}
	
}
