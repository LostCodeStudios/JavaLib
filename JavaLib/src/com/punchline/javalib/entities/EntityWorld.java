package com.punchline.javalib.entities;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.punchline.javalib.entities.systems.generic.EntitySpawnerSystem;
import com.punchline.javalib.entities.systems.physical.EntityRemovalSystem;
import com.punchline.javalib.entities.systems.physical.ParticleSystem;
import com.punchline.javalib.entities.systems.render.DebugRenderSystem;
import com.punchline.javalib.entities.systems.render.RenderSystem;
import com.punchline.javalib.entities.templates.EntityCreationArgs;
import com.punchline.javalib.entities.templates.EntityGroupTemplate;
import com.punchline.javalib.entities.templates.EntityTemplate;
import com.punchline.javalib.entities.tiles.TileMapTemplate;
import com.punchline.javalib.utils.Convert;
import com.punchline.javalib.utils.LogManager;
import com.punchline.javalib.utils.Random;
import com.punchline.javalib.utils.SpriteSheet;

/**
 * The EntityWorld is where actual gameplay happens. The world manages game
 * entities, the templates that create them, game physics, and systems that run
 * game processing.
 * 
 * @author Natman64
 * @author MadcowD
 * 
 */
public abstract class EntityWorld implements Disposable {

	// region Fields

	private Random r = new Random();

	private boolean gameOver = false;
	private GameOverInfo gameOverInfo;

	private Map<String, EntityTemplate> templates;
	private Map<String, EntityGroupTemplate> groupTemplates;

	private float timeCoefficient = 1;

	/** The InputMultiplexer managing this world's game. */
	protected InputMultiplexer input;

	/** This world's {@link EntityManager}. */
	protected EntityManager entities;

	/** This world's {@link SystemManager}. */
	protected SystemManager systems;

	/** This world's {@link ProcessManager). */
	protected ProcessManager processes;

	/** This world's {@link Camera Camera}. */
	protected Camera camera;

	/** This world's {@link PhysicsWorld} */
	protected PhysicsWorld physicsWorld;

	/** The world's {@link ContactManager}. */
	protected ContactManager contactManager;

	/** This world's {@link DebugRenderSystem} */
	protected DebugRenderSystem debugView;

	/** The {@link SpriteSheet} used for this game. */
	protected SpriteSheet spriteSheet;

	// endregion

	// region Initialization

	/**
	 * Instantiates the EntityWorld's {@link EntityManager},
	 * {@link SystemManager}, and template map.
	 * 
	 * @param input
	 *            The InputMultiplexer of the game containing this EntityWorld.
	 * @param camera
	 *            The camera that will be used for rendering this physicsWorld.
	 * @param gravity
	 *            The gravity vector2.
	 * @param doSleeping
	 *            Whether the physicsWorld allows sleeping.
	 */
	public EntityWorld(InputMultiplexer input, Camera camera, Vector2 gravity) {
		entities = new EntityManager();

		systems = new SystemManager(this);
		processes = new ProcessManager();

		templates = new HashMap<String, EntityTemplate>();
		groupTemplates = new HashMap<String, EntityGroupTemplate>();

		this.input = input;
		this.camera = camera;

		physicsWorld = new PhysicsWorld(gravity);
		contactManager = new ContactManager(this);
		processes = new ProcessManager();

		buildSpriteSheet();
		buildTemplates();
		buildSystems();
		buildEntities();
		positionCamera();
	}

	/**
	 * Positions the camera. Called by the constructor.
	 */
	protected void positionCamera() {
	}

	/**
	 * Builds the game's {@link SpriteSheet}.
	 */
	protected abstract void buildSpriteSheet();

	/**
	 * Adds necessary systems to the EntityWorld. Called by the constructor.
	 */
	protected void buildSystems() {

		// RENDER
		systems.addSystem(new RenderSystem(camera));
		debugView = (DebugRenderSystem) systems
				.addSystem(new DebugRenderSystem(input, getBox2DWorld(),
						camera, systems));

		// PHYSICAL
		systems.addSystem(new ParticleSystem());
		systems.addSystem(new EntityRemovalSystem());

		// GENERIC
		systems.addSystem(new EntitySpawnerSystem());

	}

	/**
	 * Adds necessary templates to the EntityWorld. Called by the constructor.
	 */
	protected void buildTemplates() {
		addTemplate("TileMap", new TileMapTemplate());
	}

	/**
	 * Adds necessary entities to the EntityWorld. Called by the constructor.
	 */
	protected void buildEntities() {
	}

	// endregion

	// region Disposal

	/**
	 * Disposes of all EntitySystems, and the Box2D world.
	 */
	@Override
	public void dispose() {

		for (EntityTemplate template : templates.values()) {
			template.dispose();
		}

		for (EntityGroupTemplate groupTemplate : groupTemplates.values()) {
			groupTemplate.dispose();
		}

		systems.dispose();
		physicsWorld.dispose();

	}

	// endregion

	// region Events

	/**
	 * Pauses the EntityWorld.
	 */
	public void pause() {
		systems.pause();
	}

	/**
	 * Resumes the EntityWorld.
	 */
	public void resume() {
		systems.resume();
	}

	// endregion

	// region Accessors/Mutators

	public float getTimeCoefficient() {
		return timeCoefficient;
	}

	public void setTimeCoefficient(float timeCoefficient) {
		this.timeCoefficient = timeCoefficient;
	}

	/**
	 * @return This world's boundaries, in meters.
	 */
	public abstract Rectangle getBounds();

	/**
	 * @return This world's {@link PhysicsWorld}.
	 */
	public PhysicsWorld getPhysicsWorld() {
		return physicsWorld;
	}

	/**
	 * @return The {@link SpriteSheet} used for this game.
	 */
	public SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}

	/**
	 * @return This world's Box2D {@link World}.
	 */
	public World getBox2DWorld() {
		return physicsWorld.getWorld();
	}

	/**
	 * @return This world's {@link com.badlogic.gdx.graphics.Camera Camera}.
	 */
	public Camera getCamera() {
		return camera;
	}

	/**
	 * @return Get's this world's {@link InputMultiplexer}.
	 */
	public InputMultiplexer getInput() {
		return input;
	}

	/**
	 * @return This world's entity count.
	 */
	public int getEntityCount() {
		return entities.getEntities().size;
	}

	/**
	 * @return This world's {@link ProcessManager}.
	 */
	public ProcessManager getProcessManager() {
		return processes;
	}

	/**
	 * @return This world's {@link SystemManager}.
	 */
	public SystemManager getSystemManager() {
		return systems;
	}

	/**
	 * Tries to get an entity based on its tag and/or its group and/or its type.
	 * 
	 * @param tag
	 *            The tag of the entity. "" for not inclusive search.
	 * @param group
	 *            The group of the entity. "" for not inclusive search.
	 * @param type
	 *            The type of the entity. "" for not inclusive search
	 * @return The Entity, or null.
	 */
	public Entity tryGetEntity(String tag, String group, String type) {
		return entities.tryGetEntity(tag, group, type);
	}

	/**
	 * @return Whether the game is finished.
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @return Info about why the game has ended.
	 */
	public GameOverInfo getGameOverInfo() {
		return gameOverInfo;
	}

	/**
	 * Sets the gameOverInfo field.
	 * 
	 * @param info
	 *            Info about why the game has ended.
	 */
	public void setGameOverInfo(GameOverInfo info) {
		gameOverInfo = info;
		gameOver = true;
	}

	// endregion

	// region Processing

	/**
	 * Runs all processing.
	 */
	public void process() {

		LogManager.debug("Game Loop", "Collision handling started");
		contactManager.process();
		LogManager.debug("Game Loop", "Collision handling finished");
		
		LogManager.debug("Game Loop", "System processing started");
		systems.process(entities.getNewEntities(),
				entities.getChangedEntities(), entities.getRemovedEntities(),
				Gdx.graphics.getDeltaTime() * timeCoefficient);
		LogManager.debug("Game Loop", "System processing ended");
		
		LogManager.debug("Game Loop", "Entity management started");
		entities.process();
		LogManager.debug("Game Loop", "Entity management finished");
		
		LogManager.debug("Game Loop", "Process management started");
		processes.process(this, Gdx.graphics.getDeltaTime() * timeCoefficient);
		LogManager.debug("Game Loop", "Process management finished");
		
		LogManager.debug("Game Loop", "Physics simulation started");
		physicsWorld.process(Gdx.graphics.getDeltaTime() * timeCoefficient);
		LogManager.debug("Game Loop", "Physics simulation finished");
	}

	// endregion

	// region Entity Creation

	/**
	 * Creates an {@link Entity} using the {@link EntityTemplate} associated
	 * with the given tag.
	 * 
	 * @param template
	 *            The tag of the template.
	 * @param args
	 *            Arguments for creating the {@link Entity}.
	 * @return The created entity.
	 */
	public Entity createEntity(String template, Object... args) {
		Entity e = templates.get(template).buildEntity(entities.obtain(), this,
				args);

		entities.add(e);
		return e;
	}

	/**
	 * Creates a group of Entities using the {@link EntityGroupTemplate}
	 * associated with the given tag.
	 * 
	 * @param template
	 *            The tag of the template to use.
	 * @param args
	 *            Arguments for creating the entity group.
	 * @return The group of entities.
	 */
	public Array<Entity> createEntityGroup(String template, Object... args) {
		Array<Entity> group = groupTemplates.get(template).buildEntities(this,
				args);

		for (Entity e : group) {
			entities.add(e); // Add the group to the physicsWorld.
		}

		return group;
	}

	/**
	 * Creates an Entity or group of Entities using the given
	 * {@link EntityCreationArgs}.
	 * 
	 * @param args
	 */
	public void create(EntityCreationArgs args) {
		if (args.useGroupTemplate()) {
			createEntityGroup(args.getTemplateTag(), args.getArgs());
		} else {
			createEntity(args.getTemplateTag(), args.getArgs());
		}
	}

	// endregion

	// region Template Management

	/**
	 * Adds an {@link EntityTemplate} to the template map.
	 * 
	 * @param templateKey
	 *            The template's key.
	 * @param template
	 *            The template.
	 */
	protected void addTemplate(String templateKey, EntityTemplate template) {
		templates.put(templateKey, template);
	}

	/**
	 * Adds an {@link EntityGroupTemplate} to the group template map.
	 * 
	 * @param templateKey
	 *            The template's key.
	 * @param template
	 *            The template.
	 */
	protected void addGroupTemplate(String templateKey,
			EntityGroupTemplate template) {
		groupTemplates.put(templateKey, template);
	}

	// endregion

	// region Helpers

	/**
	 * @return A random position within this world's bounds.
	 */
	public Vector2 randomPosition() {
		Rectangle bounds = getBounds();

		Vector2 pos = new Vector2();

		pos.x = r.nextFloat(bounds.x, bounds.x + bounds.width);
		pos.y = r.nextFloat(bounds.y, bounds.y + bounds.height);

		return pos;
	}

	/**
	 * @param screenCoordinates
	 *            A Vector2 whose coordinates are in screen pixels.
	 * @return A Vector2 whose coordinates are in meters and adjusted to a world
	 *         position.
	 */
	public Vector2 toWorldCoordinates(Vector2 screenCoordinates) {
		Vector2 pos = screenCoordinates.cpy();

		pos.x = screenCoordinates.x - Gdx.graphics.getWidth() / 2f
				+ camera.position.x;
		pos.y = -screenCoordinates.y + Gdx.graphics.getHeight() / 2f
				+ camera.position.y;

		return Convert.pixelsToMeters(pos);
	}

	// endregion

}
