package com.punchline.javalib.entities.systems;

import java.util.ArrayList;

import com.punchline.javalib.entities.Component;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntitySystem;

/**
 * An {@link EntitySystem} that processes {@link Entity Entities} which have specified {@link Component Components}.
 * @author Nathaniel
 *
 */
public abstract class ComponentSystem extends EntitySystem {

	ArrayList<Class<? extends Component>> componentTypes;
	
	/**
	 * Makes a ComponentSystem.
	 * @param requiredType The first required Component type.
	 * @param otherTypes Other required Component types.
	 */
	@SafeVarargs
	public ComponentSystem(Class<? extends Component> requiredType, Class<? extends Component>... otherTypes) {
		componentTypes = new ArrayList<Class<? extends Component>>();
		
		componentTypes.add(requiredType);
		
		for (Class<? extends Component> type : otherTypes) {
			componentTypes.add(type);
		}
	}
	
	@Override
	public boolean canProcess(Entity e) {
		
		for (Class<? extends Component> type : componentTypes) {
			if (!e.hasComponent(type)) return false;
		}
		
		return true;
		
	}

}
