package com.punchline.javalib.entities.processes;

import com.punchline.javalib.entities.EntityWorld;

/**
 * A process object.
 * @author MadcowD
 */
public abstract class Process {

	/**
	 * Constructs the given process with an initial delta time.
	 */
	public Process(int delay, int occurences, Process... children) {
		elapsedTicks = 0;
		this.delayTicks = delay;
		
	}
	
	//region Functioning Loop
	/**
	 * Runs an instance of the process given an entity world.
	 * @param world The world in which the process is occuring.
	 */
	public abstract void run(EntityWorld world);
	
	
	public final boolean update(EntityWorld world){
		elapsedTicks++;
		if(elapsedTicks > delayTicks)
		{
			elapsedTicks = 0;
			if(occurences != 0)
			{
				run(world);
				occurences--;
			}
			else
				return false;
		}
		return true;
	}
	
	
	//endregion
	
	//region MUTATORS

	//endregion
	
	
	
	//region FIELDS
	/**
	 * The elapsed ticks passed since last process.
	 */
	int elapsedTicks;
	
	
	/**
	 * The ticks required for a run to occur
	 */
	protected int delayTicks;
	
	/**
	 * How many occurences it takes for this thing to end.
	 */
	protected int occurences;
	
	
	
	//endregion
}
