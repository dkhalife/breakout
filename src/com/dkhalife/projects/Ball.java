package com.dkhalife.projects;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Ball {
	public static final float RADIUS = Breakout.toMeters(10);

	private Body ball;

	public Ball(float x, float y, Vec2 speed) {
		// Make a box that is subject to forces
		BodyDef boxDef = new BodyDef();
		boxDef.type = BodyType.DYNAMIC;
		boxDef.position.set(x, y);
		ball = Breakout.getWorld().createBody(boxDef);

		// Set the boundary
		PolygonShape boundary = new PolygonShape();
		boundary.setAsBox(RADIUS, RADIUS);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = boundary;
		fixtureDef.density = 1f;
		fixtureDef.userData = this;
		fixtureDef.restitution = 1;
		ball.createFixture(fixtureDef);
		
		ball.setLinearVelocity(speed);
	}

	public Fixture getFixture() {
		return ball.m_fixtureList;
	}
			
	public void draw(Graphics g) {
		Vec2 pos = ball.getPosition();
		
		Image im  = ResourceManager.getImage("BALL");
		im.rotate(ball.getAngle());
		
		im.drawCentered(Breakout.toPixels(pos.x), Breakout.toPixels(pos.y));
		
		im.rotate(-ball.getAngle());
	}
}