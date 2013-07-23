package com.punchline.javalib.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.punchline.javalib.entities.*;
import com.punchline.javalib.entities.components.render.*;

@RunWith(JUnit4.class)
public class EntityTest {

	@Test
	public void addgetComponentTest() {

			Entity e = new Entity("tag", "group", "type");
			
			Renderable x = e.addComponent(Renderable.class, new Sprite());
			Renderable p = e.getComponent();
		
			assertEquals("x != p; getComponent failure", x, p);

	}
	
	@Test
	public void onAddComponentTest() {
		
	}

}
