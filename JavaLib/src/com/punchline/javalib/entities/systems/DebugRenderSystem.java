package com.punchline.javalib.entities.systems;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntitySystem;

/**
 * System for rendering debug information.
 * @author Nathaniel
 *
 */
public final class DebugRenderSystem extends EntitySystem {

	private World world;
	private Camera camera;
	private Box2DDebugRenderer physicsDebugRenderer;
	
	public boolean enabled = false;
	
	/**
	 * Constructs a DebugRenderSystem.
	 * @param world
	 * @param camera
	 */
	public DebugRenderSystem(World world, Camera camera) {
		
		this.world = world;
		this.camera = camera;
		
		physicsDebugRenderer = new Box2DDebugRenderer();
		
	}

	@Override
	public boolean canProcess(Entity e) {
		return false;
	}

	/**
	 * Renders the physics debug view.
	 */
	@Override
	public void processEntities() {
		
		if (enabled) {
			physicsDebugRenderer.render(world, camera.combined);
			
			//TODO: Render system delta times and other debug information.
		}
		
	}
	
	
	
	
}
