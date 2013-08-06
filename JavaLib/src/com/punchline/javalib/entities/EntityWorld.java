package com.punchline.javalib.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointEdge;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.punchline.javalib.entities.systems.SystemManager;
import com.punchline.javalib.entities.systems.generic.EntitySpawnerSystem;
import com.punchline.javalib.entities.systems.physical.EntityRemovalSystem;
import com.punchline.javalib.entities.systems.physical.ParticleSystem;
import com.punchline.javalib.entities.systems.render.DebugRenderSystem;
import com.punchline.javalib.entities.systems.render.RenderSystem;
import com.punchline.javalib.entities.templates.EntityCreationArgs;
import com.punchline.javalib.entities.templates.EntityGroupTemplate;
import com.punchline.javalib.entities.templates.EntityTemplate;
import com.punchline.javalib.entities.tiles.TileMap;
import com.punchline.javalib.entities.tiles.TileMapTemplate;

/**
 * The EntityWorld is where actual gameplay happens. The world manages game
 * entities, the templates that create them, game physics, and systems that run
 * game processing.
 * @author Natman64
 * @author MadcowD
 * 
 */
public abstract class EntityWorld implements Disposable {
	
	//region Physics Constants
	
	private final float TIME_STEP = 1.0f / 60.0f;
	private final int VELOCITY_ITERATIONS = 6;
	private final int POSITION_ITERATIONS = 2;
	
	//endregion
	
	//region Fields
	
	private boolean gameOver = false;
	private GameOverInfo gameOverInfo;
	
	private Map<String, EntityTemplate> templates;
	private Map<String, EntityGroupTemplate> groupTemplates;
	
	private List<EntityCreationArgs> entitiesToCreate = new ArrayList<EntityCreationArgs>();
	private List<Body> bodiesToRemove;
	
	/**
	 * The InputMultiplexer managing this world's game.
	 */
	protected InputMultiplexer input;
	
	/**
	 * This world's {@link EntityManager}.
	 */
	protected EntityManager entities;
	
	/**
	 * This world's {@link SystemManager}.
	 */
	protected SystemManager systems;
	
	/**
	 * This world's {@link com.badlogic.gdx.graphics.Camera Camera}.
	 */
	protected Camera camera;
	
	/**
	 * This world's Box2D {@link com.badlogic.gdx.physics.box2d.World World}
	 */
	protected World physicsWorld;

	/**
	 * The world's {@link ContactManager}.
	 */
	protected ContactManager contactManager;
	
	/**
	 * This world's {@link DebugRenderSystem}
	 */
	protected DebugRenderSystem debugView;
	
	/**
	 * A reference to this world's TileMap entity, for safe refreshing. When adding a TileMap to a world,
	 * this reference must be set to the new TileMap entity.
	 */
	protected Entity map;
	
	//endregion

	//region Initialization
	
	/**
	 * Instantiates the EntityWorld's {@link EntityManager}, {@link SystemManager}, and template map.
	 * @param input The InputMultiplexer of the game containing this EntityWorld.
	 * @param camera The camera that will be used for rendering this physicsWorld.
	 * @param gravity The gravity vector2.
	 * @param doSleeping Whether the physicsWorld allows sleeping.
	 */
	public EntityWorld(InputMultiplexer input, Camera camera, Vector2 gravity, boolean doSleeping) {
		entities = new EntityManager();
		
		systems = new SystemManager(this);
		
		templates = new HashMap<String, EntityTemplate>();
		groupTemplates = new HashMap<String, EntityGroupTemplate>();
		
		this.input = input;
		this.camera = camera;
		positionCamera();
		
		physicsWorld = new World(gravity, doSleeping);
		bodiesToRemove = new ArrayList<com.badlogic.gdx.physics.box2d.Body>();
		contactManager = new ContactManager(this);
		
		buildTemplates();
		buildSystems();
		buildEntities();
	}
	
	/**
	 * Positions the camera. Called by the constructor.
	 */
	protected void positionCamera() { }
	
	/**
	 * Adds necessary systems to the EntityWorld. Called by the constructor.
	 */
	protected void buildSystems() {
		
		//RENDER
		systems.addSystem(new RenderSystem(camera));
		debugView = (DebugRenderSystem) systems.addSystem(new DebugRenderSystem(input, getPhysicsWorld(), camera, systems));
		
		//PHYSICAL
		systems.addSystem(new ParticleSystem());
		systems.addSystem(new EntityRemovalSystem());
		
		//GENERIC
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
	protected void buildEntities() { }
	
	//endregion
	
	//region Disposal
	
	/**
	 * Disposes of all EntitySystems, and the Box2D world.
	 */
	@Override
	public void dispose() {
		
		systems.dispose();
		physicsWorld.dispose();
		
	}
	
	//endregion
	
	//region Events
	
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
	
	//endregion
	
	//region Accessors/Mutators
	
	/**
	 * @return This world's boundaries, in meters.
	 */
	public abstract Rectangle getBounds();
	
	/**
	 * @return This world's Box2D {@link com.badlogic.gdx.physics.box2d.World World}
	 */
	public World getPhysicsWorld() {
		return physicsWorld;
	}
	
	/**
	 * @return This world's TileMap.
	 */
	public TileMap getMap() {
		return map.getComponent(TileMap.class);
	}
	
	/**
	 * @return This world's {@link com.badlogic.gdx.graphics.Camera Camera}.
	 */
	public Camera getCamera() {
		return camera;
	}
	
	/**
	 * The entity count in the physicsWorld.
	 * @return The count of entities active in the physicsWorld.
	 */
	public int getEntityCount(){
		return entities.getEntities().size();
	}
	
	/**
	 * Tries to get an entity based on its tag and/or its group and/or its type.
	 * @param tag The tag of the entity. "" for not inclusive search.
	 * @param group The group of the entity. "" for not inclusive search.
	 * @param type The type of the entity. "" for not inclusive search
	 * @return
	 */
	public Entity tryGetEntity(String tag, String group, String type){
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
	 * @param info Info about why the game has ended.
	 */
	public void setGameOverInfo(GameOverInfo info) {
		gameOverInfo = info;
		gameOver = true;
	}
	
	//endregion

	//region Processing
	
	/**
	 * Runs all system processing.
	 */
	public void process() {
		
		systems.process(
				entities.getNewEntities(), 
				entities.getChangedEntities(), 
				entities.getRemovedEntities(), Gdx.graphics.getDeltaTime());
		
		entities.process();
		
		//REMOVE BODIES SAFELY
		safelyRemoveBodies();
		
		//CREATE NEW ENTITIES SAFELY
		safelyCreateEntities();
		
		//REFRESH THE TILEMAP SAFELY
		if (map != null) {
			TileMap tileMap = ((TileMap) map.getComponent(TileMap.class));
			if (tileMap.isChanged())
				tileMap.refresh();
		}
		
		physicsWorld.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
	}
	
	//endregion

	//region Safe Body Removal
	
	/**
	 * Marks a body for safe deletion.
	 * @param Body The body to be removed.
	 */
	public void safelyRemoveBody(com.badlogic.gdx.physics.box2d.Body body){
		if(!bodiesToRemove.contains(body))
			bodiesToRemove.add(body);
	}
	
	private void safelyRemoveBodies() {
		for(int i = 0; i < bodiesToRemove.size(); i++){
			Body body = bodiesToRemove.get(i);
		    //to prevent some obscure c assertion that happened randomly once in a blue moon
		    final ArrayList<JointEdge> list = body.getJointList();
		    while (list.size() > 0) {
		        physicsWorld.destroyJoint(list.get(0).joint);
		    }
		    // actual remove
		    physicsWorld.destroyBody(body);
		}
		bodiesToRemove.clear();
	}
	
	//endregion
	
	//region Entity Creation

	/**
	 * Creates an {@link Entity} using the {@link EntityTemplate} associated with the given tag.
	 * @param template The tag of the template.
	 * @param args Arguments for creating the {@link Entity}.
	 * @return The created entity.
	 */
	public Entity createEntity(String template, Object... args) {
		Entity e = templates.get(template).buildEntity(entities.obtain(), this, args); //Grab an entity from the entity pool and send it
		entities.add(e);
		return e;
	}
	
	/**
	 * Creates a group of Entities using the {@link EntityGroupTemplate} associated with the given tag.
	 * @param template The tag of the template to use.
	 * @param args Arguments for creating the entity group.
	 * @return The group of entities.
	 */
	public ArrayList<Entity> createEntityGroup(String template, Object... args) {
		ArrayList<Entity> group = groupTemplates.get(template).buildEntities(this, args);
		
		for (Entity e : group) {
			entities.add(e); //Add the group to the physicsWorld.
		}
		
		return group;
	}

	/**
	 * Creates an Entity or group of Entities using the given {@link EntityCreationArgs}.
	 * @param args
	 */
	public void create(EntityCreationArgs args) {
		if (args.useGroupTemplate()) {
			createEntityGroup(args.getTemplateTag(), args.getArgs());
		} else {
			createEntity(args.getTemplateTag(), args.getArgs());
		}
	}
	
	/**
	 * Safely creates an Entity using the {@link EntityTemplate} associated with the given tag.
	 * @param template The tag of the template to use.
	 * @param args Arguments for creating the entity.
	 */
	public void safelyCreateEntity(String template, Object... args) {
		entitiesToCreate.add(new EntityCreationArgs(template, false, args));
	}
	
	/**
	 * Safely creates a group of Entities using the {@link EntityGroupTemplate} associated with the given tag.
	 * @param template The tag of the template to use.
	 * @param args Arguments for creating the entity group.
	 */
	public void safelyCreateEntityGroup(String template, Object... args) {
		entitiesToCreate.add(new EntityCreationArgs(template, true, args));
	}
	
	/**
	 * Safely creates an Entity or group of Entities using the given {@link EntityCreationArgs}.
	 * Use this method when creating Entities that have Body components, or you may cause a crash.
	 * @param args
	 */
	public void safeCreate(EntityCreationArgs args) {
		entitiesToCreate.add(args);
	}
	
	private void safelyCreateEntities() {
		for (EntityCreationArgs args : entitiesToCreate) {
			if (args.useGroupTemplate()) {
				createEntityGroup(args.getTemplateTag(), args.getArgs());
			} else {
				createEntity(args.getTemplateTag(), args.getArgs());
			}
		}
		
		entitiesToCreate.clear();
	}
	
	//endregion
	
	//region Template Management
	
	/**
	 * Adds an EntityTemplate to the template map.
	 * @param templateKey The template's key.
	 * @param template The template.
	 */
	public void addTemplate(String templateKey, EntityTemplate template) {
		templates.put(templateKey, template);
	}
	
	/**
	 * Adds an EntityGroupTemplate to the group template map.
	 * @param templateKey The template's key.
	 * @param template The template.
	 */
	public void addGroupTemplate(String templateKey, EntityGroupTemplate template) {
		groupTemplates.put(templateKey, template);
	}

	//endregion
	
}
