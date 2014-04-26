package com.dkhalife.projects;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Wall {
	private Body wall;

	private float width;
	private float height;

	public Wall(float x, float y, float width, float height) {
		this.width = Breakout.toPixels(width);
		this.height = Breakout.toPixels(height);

		// Make a box that is subject to forces
		BodyDef boxDef = new BodyDef();
		boxDef.type = BodyType.STATIC;
		boxDef.position.set(x, y);
		wall = Breakout.getWorld().createBody(boxDef);

		// Set the boundary
		PolygonShape boundary = new PolygonShape();
		boundary.setAsBox(width / 2, height / 2);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = boundary;
		fixtureDef.density = 1f;
		fixtureDef.userData = this;
		fixtureDef.restitution = 0;
		wall.createFixture(fixtureDef);
	}

	public Fixture getFixture() {
		return wall.m_fixtureList;
	}

	public void draw(Graphics g) {
		g.setColor(Color.gray);

		Vec2 pos = wall.getPosition();
		g.fillRect(Breakout.toPixels(pos.x) - width / 2, Breakout.toPixels(pos.y) - height / 2, width, height);
	}
}
