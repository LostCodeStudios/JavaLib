package com.punchline.javalib.entities.components.generic;

import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.ComponentManager;
import com.punchline.javalib.entities.components.abstracted.Stat;

/**
 * A component describing any kind of cooldown.
 * @author GenericCode
 * @created September 25, 2013
 */

public class Cooldown extends Stat {
	
	private Entity owner;
	private EntityWorld world;
	
	public Cooldown(Entity owner, EntityWorld world, float max){
		super(max);
		
		this.owner = owner;
		this.world = world;
	}
	
	public boolean tick(){
		if(this.getCurrentValue()>0)
			super.drain(1);
		if(this.isEmpty())
			return true;
		return false;
	}
	@Override
	public void onAdd(ComponentManager container) {}

	@Override
	public void onRemove(ComponentManager container) {}

}
