package com.punchline.javalib.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.*;
import com.punchline.javalib.entities.components.physical.Particle;
import com.punchline.javalib.entities.components.physical.Transform;
import com.punchline.javalib.entities.components.physical.Velocity;
import com.punchline.javalib.entities.components.render.*;

@RunWith(JUnit4.class)
public class EntityTest {

	@Test
	public void spritePositionTest() {
		Sprite sprite = new Sprite(new Texture(Gdx.files.internal("data/lofi_scifi_v2_trans.png")), new Rectangle(0, 0, 50, 50));
		
		sprite.setPosition(new Vector2(25, 25));
		
		Vector2 pos = sprite.getPosition();
		Vector2 p = new Vector2(25, 25);
		
		assertEquals("setPosition failure", pos, p);
	}
	
	@Test
	public void addgetComponentTest() {
			Entity e = new Entity();
			e.init("tag", "group", "type");
			//ha
			Renderable x = (Renderable)e.addComponent(new Sprite());
			Renderable p = (Renderable)e.getComponent(Renderable.class);
		
			assertEquals("x != p; getComponent failure", x, p);

	}
	
	@Test
	public void onAddComponentTest() {
		Entity e = new Entity();
		e.init("tag", "group", "type");
		
		Particle p = (Particle)e.addComponent(new Particle(e, new Vector2(0,0), 0f, new Vector2(0,0)));
		Velocity v = (Velocity)e.getComponent(Velocity.class);
		Transform t = (Transform)e.getComponent(Transform.class);
		p.setAngularVelocity(22);
		v.setAngularVelocity(43);
		
		assertEquals("p != t; onAddComponent failure", p, t);
		assertEquals("v != t; onAddComponent failure", v, t);
	}

}
