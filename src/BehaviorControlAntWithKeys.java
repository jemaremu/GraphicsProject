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

public  class BehaviorControlAntWithKeys extends Behavior {
	private WakeupOnAWTEvent keyEvent;
	BehaviorAnimateAnt bAntController;

	public BehaviorControlAntWithKeys(TransformGroup g) {
		bAntController = new BehaviorAnimateAnt(g);
		g.addChild(bAntController);
		keyEvent = new WakeupOnAWTEvent(java.awt.event.KeyEvent.KEY_PRESSED);
	}

	public void initialize() {
		Bounds doEverywhere = new BoundingSphere(new Point3d(0.0,0.0,0.0),100.0);
		this.setSchedulingBounds(doEverywhere);
		wakeupOn(keyEvent);
	}

	public void processStimulus(Enumeration criteria) {
		AWTEvent events[]=keyEvent.getAWTEvent();
		char c = ((KeyEvent)events[0]).getKeyChar();

		switch (c) {
		case 'w':
			bAntController.accelerate();
			break;
		case 'd':
			bAntController.addRight();
			break;
		case 'a':
			bAntController.addLeft();
			break;
		case 's':
			bAntController.stop();
			break;
		default:
			break;
		}
		wakeupOn(keyEvent);
	}
}





