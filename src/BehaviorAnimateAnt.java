/* credit to Instructor: Jesse Allen
 * modified by Adriana Carolina Camacho
 * last modified: 11/24/2013
 */
import javax.media.j3d.*;
import javax.vecmath.*;
import java.util.Enumeration;

public  class BehaviorAnimateAnt extends Behavior
{
	private TransformGroup transGroup;
	private Transform3D trRotate = new Transform3D();
	private Transform3D trPosition = new Transform3D();
	private Transform3D trCompound = new Transform3D();
	private WakeupOnElapsedFrames frameEvent;
	
	private double direction = 0.0;
	private double speed = 0.0;
	private double x =0.0;
	private double y = 0.0;
	private double z = 0.0;
	private double wheelAngle = 0.0;
	private double falling = 0.0;
	
	public BehaviorAnimateAnt(TransformGroup g)
	{
		frameEvent = new WakeupOnElapsedFrames(1);
		transGroup = g;
	}
	
	public void initialize()
	{
		Bounds doEverywhere = new BoundingSphere(new Point3d(0.0,0.0,0.0),100.0);
		this.setSchedulingBounds(doEverywhere);
		wakeupOn(frameEvent);
	}
	
	public void processStimulus(Enumeration criteria)
	{		
		//x += speed + direction;
		x += speed * Math.cos(direction * Math.PI/180.0);
		//y += speed + direction;
		y += speed * Math.sin(direction * Math.PI/180.0);
		
		z -= falling;
		
		// Direction only changes as the car moves forward
		// with the wheel turned
		direction = direction + wheelAngle * speed * 10.0;
		
		RepositionTheAnt();
		
		wakeupOn(frameEvent);
	}

	public void addRight()
	{
		if (wheelAngle > -10.0) wheelAngle -= .05;
	}
	
	public void addLeft()
	{
		if (wheelAngle < 10.0) wheelAngle += .05;
	}
	
	public void accelerate()
	{
		if (speed < 5.0) speed += 0.09;
	}
	
	public void stop()
	{
		speed=0.0;
		wheelAngle = 0.0;
	}
	
	public void fall()
	{
		falling += 1;
	}
	
	private void RepositionTheAnt()
	{
		trPosition.set(new Vector3d(x,y,z));
		
		//trRotate.set(direction);
		trRotate.rotZ(direction * Math.PI/ 180.0);
		trCompound.mul(trPosition,trRotate); 
		transGroup.setTransform(trCompound);
	}
}