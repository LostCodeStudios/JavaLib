package com.punchline.javalib.entities;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.physics.box2d.World;

//TODO: Make abstract
public class EntityWorld {

	/**
	 * This world's {@link EntityManager}.
	 */
	protected EntityManager entities;
	
	/**
	 * This world's {@link SystemManager}.
	 */
	protected SystemManager systems;
	
	/**
	 * This world's Box2D {@link com.badlogic.gdx.physics.box2d.World World}
	 */
	protected World physicsWorld;
	
	/**
	 * Template map.
	 */
	private Map<String, EntityTemplate> templates;
	
	/**
	 * Instantiates the EntityWorld's {@link EntityManager}, {@link SystemManager}, and template map.
	 */
	public EntityWorld() {
		entities = new EntityManager();
		
		systems = new SystemManager();
		
		templates = new HashMap<String, EntityTemplate>();
		
		buildComponents();
		buildSystems();
		buildTemplates();
		buildEntities();
	}
	
	/**
	 * Runs all system processing.
	 */
	public void process() {
		
		entities.process();
		
		systems.process(
				entities.getNewEntities(), 
				entities.getChangedEntities(), 
				entities.getRemovedEntities());
		
	}
	
	/**
	 * @return This world's Box2D {@link com.badlogic.gdx.physics.box2d.World World}
	 */
	public World getPhysicsWorld() {
		return physicsWorld;
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
	
	/**
	 * Adds necessary components to the world. Called by the constructor.
	 */
	protected void buildComponents() { }
	
	/**
	 * Adds necessary systems to the world. Called by the constructor.
	 */
	protected void buildSystems() { }
	
	/**
	 * Adds necessary templates to the world. Called by the constructor.
	 */
	protected void buildTemplates() { }
	
	/**
	 * Adds necessary entities to the world. Called by the constructor.
	 */
	protected void buildEntities() { }
	
}
