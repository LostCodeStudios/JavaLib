package com.punchline.javalib.entities.systems;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntitySystem;
import com.punchline.javalib.entities.SystemManager;

/**
 * System for rendering debug information.
 * @author Nathaniel
 *
 */
public final class DebugRenderSystem extends EntitySystem {

	private World world;
	private Camera camera;
	private SystemManager systems;
	
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	private Box2DDebugRenderer physicsDebugRenderer;
	
	public boolean enabled = false;
	
	/**
	 * Constructs a DebugRenderSystem.
	 * @param world The EntityWorld that is being debugged.
	 * @param camera The EntityWorld's main camera.
	 * @param systems The world's SystemManager.
	 */
	public DebugRenderSystem(World world, Camera camera, SystemManager systems) {
		
		this.world = world;
		this.camera = camera;
		this.systems = systems;
		
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		physicsDebugRenderer = new Box2DDebugRenderer();
		
	}
	
	@Override
	public void dispose() {
		spriteBatch.dispose();
		font.dispose();
		physicsDebugRenderer.dispose();
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
		
		super.processEntities();
		
		if (enabled) {
			physicsDebugRenderer.render(world, camera.combined);
			
			//TODO: Render system delta times and other debug information.
			Map<String, Float> performance = systems.systemPerformance();			
			
			spriteBatch.begin();
			
			int i = 1;
			String systemName = "";
			Float systemPerformance = 0f;
			
			Entry<String, Float> next;
			Iterator<Entry<String, Float>> iter = performance.entrySet().iterator();
			
			while (iter.hasNext()) {
				
				next = iter.next();
				systemName = next.getKey();
				systemPerformance = next.getValue();
				
				font.draw(spriteBatch, systemName + ": " + systemPerformance.toString(), 0, i * font.getLineHeight());
				
				i++;
			}
			
			spriteBatch.end();
		}
		
	}
	
}
