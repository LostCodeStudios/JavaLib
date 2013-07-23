package com.punchline.javalib.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.punchline.javalib.entities.*;
import com.punchline.javalib.entities.components.render.Sprite;
import com.punchline.javalib.utils.Units;

@SuppressWarnings("unused")
@RunWith(JUnit4.class)
public class EntityTest {

	@Test
	public void addComponentTest() {
		
		assertTrue(Units.screenToWorld(32) == 1);
		assertTrue(Units.screenToWorld(32) != 0);
		
	}

}
