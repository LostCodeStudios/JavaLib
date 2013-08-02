package com.punchline.javalib.entities.components.physical;

import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.Component;
import com.punchline.javalib.entities.components.ComponentManager;

/**
 * TODO WIP
 * Component wrapper for a sensor fixture that triggers an event in the EntityWorld.
 * @author Natman64
 * @created Aug 1, 2013
 */
public class TriggerZone implements Component {

	/**
	 * Callback interface for an event triggered by a Trigger Zone.
	 * @author Natman64
	 * @created Aug 1, 2013
	 */
	public interface TriggerCallback {
		
		/**
		 * Called when a zone is triggered.
		 * @param world The world that contains the zone.
		 */
		public void invoke(EntityWorld world);
		
	}
	
	private com.badlogic.gdx.physics.box2d.Body body;
	private com.badlogic.gdx.physics.box2d.Fixture fixture;
	
	@Override
	public void onAdd(ComponentManager container) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRemove(ComponentManager container) {
		// TODO Auto-generated method stub

	}

}
