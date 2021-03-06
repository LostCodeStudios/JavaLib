package com.lostcode.javalib.entities.systems.render;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.SystemManager;
import com.lostcode.javalib.entities.systems.InputSystem;
import com.lostcode.javalib.utils.Convert;

/**
 * System for rendering debug information.
 * 
 * @author Natman64
 * 
 */
public final class DebugRenderSystem extends InputSystem {

	// region Fields

	private World physicsWorld;
	private Camera camera;
	private SystemManager systems;

	private SpriteBatch spriteBatch;
	private BitmapFont font;
	private Box2DDebugRenderer physicsDebugRenderer;
	private Vector2 mousePosition;

	public boolean enabled = false;
	public boolean visible = false;

	// endregion

	// region Initialization/Disposal

	/**
	 * Constructs a DebugRenderSystem.
	 * 
	 * @param input
	 *            The game's current InputMultiplexer.
	 * @param physicsWorld
	 *            The EntityWorld that is being debugged.
	 * @param camera
	 *            The EntityWorld's main camera.
	 * @param systems
	 *            The physicsWorld's SystemManager.
	 */
	public DebugRenderSystem(InputMultiplexer input, World world,
			Camera camera, SystemManager systems) {

		super(input);

		this.physicsWorld = world;
		this.camera = camera;

		this.systems = systems;

		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(1f, 1f, 1f, 0.5f);

		if (Gdx.app.getType() == ApplicationType.Android) {
			font.scale(3); // draw bigger text for the tiny screen :)
		}

		physicsDebugRenderer = new Box2DDebugRenderer();

		mousePosition = new Vector2();

	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		font.dispose();
		physicsDebugRenderer.dispose();
		super.dispose();
	}

	// endregion

	// region Processing

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

				Matrix4 renderMatrix = camera.combined.cpy();
				renderMatrix.scl(Convert.getPixelMeterRatio());

				physicsDebugRenderer.render(physicsWorld, renderMatrix);

				Map<String, Float> performance = systems.systemPerformance();

				spriteBatch.begin();
				
				int i = 1;

				if (Gdx.app.getType() == ApplicationType.Android) {
					i = 7; // Start drawing text further up to avoid being
							// covered by UI elements.
				} else { // don't draw system performance on android.

					String systemName = "";
					Float systemPerformance = 0f;

					Entry<String, Float> next;
					Iterator<Entry<String, Float>> iter = performance
							.entrySet().iterator();

					while (iter.hasNext()) {

						next = iter.next();
						systemName = next.getKey();
						systemPerformance = next.getValue();

						font.draw(spriteBatch, systemName + ": "
								+ systemPerformance.toString(), 0,
								i * font.getLineHeight());

						i++;
					}
				}

				font.draw(spriteBatch,
						"FPS: " + Gdx.graphics.getFramesPerSecond(), 0, i++
								* font.getLineHeight());
				font.draw(spriteBatch,
						"Entities: " + this.world.getEntityCount(), 0, i++
								* font.getLineHeight());
				font.draw(spriteBatch,
						"Camera pos: " + camera.position.toString(), 0, i++
								* font.getLineHeight());
				font.draw(spriteBatch,
						"Mouse pos: " + mousePosition.toString(), 0,
						i++ * font.getLineHeight());

				// real mouse position
				font.draw(
						spriteBatch,
						"World mouse pos: "
								+ world.toWorldCoordinates(mousePosition), 0,
						i++ * font.getLineHeight());

				spriteBatch.end();

			}
		}
	}

	@Override
	protected void process(Entity e) {
	}

	// endregion

	// region Input

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.F1) {
			visible = !visible;
			return true;
		}

		return false;
	}

	@Override
	public boolean mouseMoved(int x, int y) {
		mousePosition.x = x;
		mousePosition.y = y;
		return false;
	}

	// endregion

}
