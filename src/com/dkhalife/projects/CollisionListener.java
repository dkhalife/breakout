package com.dkhalife.projects;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

public class CollisionListener implements ContactListener {
	@Override
	public void beginContact(Contact contact) {
		if(contact.m_fixtureB.m_userData.getClass().getName() == "com.dkhalife.projects.Brick"){
			((Brick) contact.m_fixtureB.m_userData).hit();
		}
		if(contact.m_fixtureA.m_userData.getClass().getName() == "com.dkhalife.projects.Brick"){
			((Brick) contact.m_fixtureA.m_userData).hit();
		}
	}

	@Override
	public void endContact(Contact contact) {}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {}

}
