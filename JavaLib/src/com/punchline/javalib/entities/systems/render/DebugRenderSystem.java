package com.punchline.javalib.entities.systems.render;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntitySystem;
import com.punchline.javalib.entities.SystemManager;
import com.punchline.javalib.utils.Convert;

/**
 * System for rendering debug information.
 * @author Nathaniel
 *
 */
public final class DebugRenderSystem extends EntitySystem implements InputProcessor {

	private World world;
	private Camera camera;
	private Camera debugCamera;
	private SystemManager systems;
	
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	private Box2DDebugRenderer physicsDebugRenderer;
	
	public boolean enabled = false;
	public boolean visible = false;
	
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
		font.setColor(1f, 1f, 1f, 0.5f);
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
			
			if (visible) {
				
				debugCamera = new OrthographicCamera(
						Convert.pixelsToMeters(camera.viewportWidth), 
						Convert.pixelsToMeters(camera.viewportHeight));
				debugCamera.position.set(Convert.pixelsToMeters(camera.position));
				
				physicsDebugRenderer.render(world, debugCamera.combined);
				
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
				
				font.draw(spriteBatch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 0, i * font.getLineHeight());
				
				spriteBatch.end();
				
			}
		}
	}

	@Override
	protected void process(Entity e) {
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.F1) {
			visible = !visible;
			return true;
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
