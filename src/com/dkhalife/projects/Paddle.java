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

public class Paddle {
	public static final float HEIGHT = Breakout.toMeters(15);

	public static enum SIZE {
		PADDLE_SMALL, PADDLE_NORMAL, PADDLE_LARGE
	};

	private SIZE size = SIZE.PADDLE_NORMAL;

	private Body paddle;

	public Paddle(float x, float y) {
		// Make a box that is subject to forces
		BodyDef boxDef = new BodyDef();
		boxDef.type = BodyType.DYNAMIC;
		boxDef.position.set(x, y);
		paddle = Breakout.getWorld().createBody(boxDef);

		// Set the boundary
		PolygonShape boundary = new PolygonShape();
		boundary.setAsBox(getWidth() / 2, HEIGHT / 2);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = boundary;
		fixtureDef.density = 1f;
		fixtureDef.userData = this;
		fixtureDef.restitution = 1;
		paddle.createFixture(fixtureDef);
	}

	public void setSize(SIZE size) {
		this.size = size;

		PolygonShape boundary = new PolygonShape();
		boundary.setAsBox(getWidth() / 2, HEIGHT / 2);
		paddle.m_fixtureList.m_shape = boundary;
	}

	public Fixture getFixture() {
		return paddle.m_fixtureList;
	}

	public float getWidth() {
		switch (size) {
			case PADDLE_SMALL:
				return Breakout.toMeters(30);

			case PADDLE_LARGE:
				return Breakout.toMeters(90);

			default:
				return Breakout.toMeters(60);
		}
	}

	public void setSpeed(Vec2 speed) {
		paddle.setLinearVelocity(speed);
	}

	public void draw(Graphics g) {
		Image im;

		switch (size) {
			case PADDLE_SMALL:
				im = ResourceManager.getImage("PADDLE_SMALL");
			break;

			case PADDLE_LARGE:
				im = ResourceManager.getImage("PADDLE_LARGE");
			break;

			default:
				im = ResourceManager.getImage("PADDLE_NORMAL");
			break;
		}

		Vec2 pos = paddle.getPosition();
		im.drawCentered(Breakout.toPixels(pos.x), Breakout.toPixels(pos.y));
	}
}
