package com.punchline.javalib.entities.systems.generic;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.systems.InputSystem;

/**
 * System for moving the camera based on keyboard input from the arrow keys.
 * @author Nathaniel
 * @created Jul 23, 2013
 */
public class CameraMovementSystem extends InputSystem  {

	private static final float CAMERA_SPEED = 200f;
	
	private Camera camera;
	private Rectangle bounds;
	
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean movingUp = false;
	private boolean movingDown = false;
	
	/**
	 * Makes a CameraMovementSystem
	 * @param input the game's current InputMultiplexer.
	 * @param camera The camera that this system will control.
	 */
	public CameraMovementSystem(InputMultiplexer input, Camera camera, Rectangle bounds) {
		super(input);
		this.camera = camera;
		this.bounds = bounds;
	}

	@Override
	public boolean canProcess(Entity e) {
		return false; //Doesn't actually process Entities
	}

	@Override
	public void processEntities() {
		super.processEntities();
		
		Vector3 movement = new Vector3(0, 0, 0);
		
		if (movingLeft) {
			movement.x = - 1;
		} else if (movingRight) {
			movement.x = 1;
		}
		
		if (movingUp){
			movement.y = 1;
		} else if (movingDown) {
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
	public boolean keyDown(int keycode) {
		if (keycode == Keys.LEFT) {
			movingLeft = true;
			return true;
		} else if (keycode == Keys.RIGHT){
			movingRight = true;
			return true;
		}
		
		if (keycode == Keys.UP) {
			movingUp = true;
			return true;
		} else if (keycode == Keys.DOWN) {
			movingDown = true;
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.LEFT) {
			movingLeft = false;
			return true;
		} else if (keycode == Keys.RIGHT){
			movingRight = false;
			return true;
		}
		
		if (keycode == Keys.UP) {
			movingUp = false;
			return true;
		} else if (keycode == Keys.DOWN) {
			movingDown = false;
			return true;
		}
		
		return false;
	}
	
}
