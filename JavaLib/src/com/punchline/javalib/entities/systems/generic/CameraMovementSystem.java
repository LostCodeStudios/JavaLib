package com.punchline.javalib.entities.systems.generic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntitySystem;

/**
 * System for moving the camera based on keyboard input from the arrow keys.
 * @author Nathaniel
 * @created Jul 23, 2013
 */
public class CameraMovementSystem extends EntitySystem {

	private static final float CAMERA_SPEED = 200f;
	
	private Camera camera;
	private Rectangle bounds;
	
	/**
	 * Makes a CameraMovementSystem
	 * @param camera The camera that this system will control.
	 */
	public CameraMovementSystem(Camera camera, Rectangle bounds) {
		this.camera = camera;
		this.bounds = bounds;
	}
	
	@Override
	public void dispose() { }

	@Override
	public boolean canProcess(Entity e) {
		return false; //Doesn't actually process Entities
	}

	@Override
	public void processEntities() {
		super.processEntities();
		
		Vector3 movement = new Vector3(0, 0, 0);
		
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			movement.x = - 1;
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			movement.x = 1;
		}
		
		if (Gdx.input.isKeyPressed(Keys.UP)){
			movement.y = 1;
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			movement.y = -1;
		}
		
		movement.nor();
		movement.mul(CAMERA_SPEED);
		movement.mul(deltaSeconds());
		
		camera.translate(movement);
		
		float left = camera.position.x - camera.viewportWidth / 2;
		float bottom = camera.position.y - camera.viewportHeight / 2;
		float right = left + camera.viewportWidth;
		float top = bottom + camera.viewportHeight;
		
		if (left < bounds.x) {
			camera.position.x = bounds.x + camera.viewportWidth / 2;
		}
		
		if (top > bounds.y + camera.viewportHeight) {
			camera.position.y = bounds.y + camera.viewportHeight - camera.viewportHeight / 2;
		}
		
		if (right > bounds.x + bounds.width) {
			camera.position.x = bounds.x + bounds.width - camera.viewportWidth / 2;
		}
		
		if (bottom < bounds.y) {
			camera.position.y = bounds.y + camera.viewportHeight / 2;
		}
		
	}

	@Override
	protected void process(Entity e) { }
	
}
