package com.punchline.javalib.entities.processes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.physical.Transform;
import com.punchline.javalib.entities.components.physical.Velocity;

public class PatrolProcess extends Process {

	/** Entities will be considered as having reached a patrol point if they come within this many meters. */
	private static final float ERROR_TOLERANCE = 0.2f;
	
	private Entity patroller;
	private float patrolSpeed;
	
	private int nextPoint = 0;
	private Array<Vector2> patrolPoints = new Array<Vector2>();
	private Array<Float> waitingTimes = new Array<Float>();
	
	public PatrolProcess(Entity patroller, float patrolSpeed, float waitingTime, Vector2... patrolPoints) {
		this.patroller = patroller;
		
		if (missingComponents(patroller)) {
			throw new IllegalArgumentException("Tried to attach a patrol process to an entity without either a transform or velocity component.");
		}
		
		this.patrolSpeed = patrolSpeed;
		
		for (Vector2 point : patrolPoints) {
			this.patrolPoints.add(point);
			this.waitingTimes.add(waitingTime);
		}
	}
	
	public void setWaitingTime(int pointIndex, float time) {
		waitingTimes.set(pointIndex, time);
	}
	
	@Override
	public void update(EntityWorld world, float deltaTime) {
		if (missingComponents(patroller)) {
			throw new IllegalArgumentException("Cannot run PatrolProcess - entity is missing either a transform or velocity component.");
		}
		
		Transform t = patroller.getComponent(Transform.class);
		Velocity v = patroller.getComponent(Velocity.class);
		
		Vector2 destination = patrolPoints.get(nextPoint);
		
		if (destination.dst(t.getPosition()) < ERROR_TOLERANCE) {
			int currentPoint = nextPoint;
			nextPoint = ++nextPoint % patrolPoints.size;
			
			end(ProcessState.ABORTED);
			world.getProcessManager().attach(new DelayProcess(waitingTimes.get(currentPoint), this));
			return;
		}
		
		Vector2 velocity = destination.cpy().sub(t.getPosition());
		velocity.nor();
		velocity.scl(patrolSpeed);
		
		v.setLinearVelocity(velocity);
		
		t.setRotation((float) Math.toRadians(velocity.angle()));
		
	}

	private boolean missingComponents(Entity e) {
		return !e.hasComponent(Transform.class) || !e.hasComponent(Velocity.class);
	}
	
}
