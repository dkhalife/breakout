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

public class Brick {
	public static final float WIDTH = Breakout.toMeters(60);
	public static final float HEIGHT = Breakout.toMeters(20);

	private Body brick;

	private int maxHits;
	private int currentHits = 0;

	public Brick(float x, float y, int maxHits) {
		this.maxHits = maxHits;

		// Make a box that is subject to forces
		BodyDef boxDef = new BodyDef();
		boxDef.type = BodyType.STATIC;
		boxDef.position.set(x, y);
		brick = Breakout.getWorld().createBody(boxDef);

		// Set the boundary
		PolygonShape boundary = new PolygonShape();
		boundary.setAsBox(WIDTH / 2, HEIGHT / 2);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = boundary;
		fixtureDef.density = 1f;
		fixtureDef.userData = this;
		fixtureDef.restitution = 0;
		brick.createFixture(fixtureDef);
	}

	public void hit() {
		System.out.println("Hit!");
		
		if (++currentHits >= maxHits) {
			// Remove it from the list of bricks
			PlayingState.destroyBrick(this);
		}
	}

	public Body getBody() {
		return brick;
	}

	public Fixture getFixture() {
		return brick.m_fixtureList;
	}

	public void draw(Graphics g) {
		Image im;

		switch (maxHits - currentHits) {
			default:
			case 1:
				im = ResourceManager.getImage("BRICK_BLUE");
			break;

			case 2:
				im = ResourceManager.getImage("BRICK_CYAN");
			break;

			case 3:
				im = ResourceManager.getImage("BRICK_GREEN");
			break;

			case 4:
				im = ResourceManager.getImage("BRICK_YELLOW");
			break;

			case 5:
				im = ResourceManager.getImage("BRICK_MAGENTA");
			break;

			case 6:
				im = ResourceManager.getImage("BRICK_RED");
			break;
		}

		Vec2 pos = brick.getPosition();
		im.drawCentered(Breakout.toPixels(pos.x), Breakout.toPixels(pos.y));
	}
}
