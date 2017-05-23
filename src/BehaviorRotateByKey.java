/* Course: CS 3370/Fall 2013
   modified by Adriana Carolina Camacho 
   Assignment 8: Animation
   Instructor: Jesse Allen
   Date of last modification: Nov 24 2013
 * */
import javax.media.j3d.*;
import javax.vecmath.*;
import java.util.Enumeration;
import java.awt.AWTEvent;
import java.awt.event.*;

public  class BehaviorRotateByKey extends Behavior {
	private TransformGroup transGroup;
	private WakeupOnAWTEvent keyEvent;

	public BehaviorRotateByKey(TransformGroup g) {
		// Create an event object that will hold event details
		// Intitialize it to indicate that we want key press events.
		keyEvent = new WakeupOnAWTEvent(java.awt.event.KeyEvent.KEY_PRESSED);

		// Keep a copy of the transform group that we will manipulate 
		transGroup = g;
		transGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		transGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	}

	public void initialize() {
		// Set a large bounds that will always be in view,
		// causing this behavior to take place
		Bounds doEverywhere = new BoundingSphere(new Point3d(0.0,0.0,0.0),100.0);
		this.setSchedulingBounds(doEverywhere);

		// Tell the system to wake this object up (call the
		// processStimulus method) upon the event
		wakeupOn(keyEvent);
	}

	public void processStimulus(Enumeration criteria) {
		// A key has been pressed, get the details
		AWTEvent events[]=keyEvent.getAWTEvent();
		char c = ((KeyEvent)events[0]).getKeyChar();
		System.out.println("char = " + c);

		switch (c) {
		case 'x':
			rotateX(10.0f);
			break;
		case 'X':
			rotateX(-10.0f);
			break;
		case 'y':
			rotateY(10.0f);
			break;
		case 'Y':
			rotateY(-10.0f);
			break;
		case 'z':
			rotateZ(10.0f);
			break;
		case 'Z':
			rotateZ(-10.0f);
			break;
		default:
			break;
		}

		// Tell the system to wake us up at the next qualifying event
		wakeupOn(keyEvent);
	}

	private void rotateX(float angle) {
		Transform3D trans = new Transform3D();
		trans.rotX(angle * Math.PI / 180.0);
		compoundTransform(trans);
	}

	private void rotateY(float angle) {
		Transform3D trans = new Transform3D();
		trans.rotY(angle * Math.PI / 180.0);
		compoundTransform(trans);
	}

	private void rotateZ(float angle) {
		Transform3D trans = new Transform3D();
		trans.rotZ(angle * Math.PI / 180.0);
		compoundTransform(trans);
	}

	private void compoundTransform(Transform3D transform2) {
		// Compound the incoming translation with the existing transformation 
		Transform3D transform1 = new Transform3D();
		transGroup.getTransform(transform1);

		transform2.mul(transform1);
		transGroup.setTransform(transform2);
	}
}





