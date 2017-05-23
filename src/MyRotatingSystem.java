
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

public class MyRotatingSystem
{
	public TransformGroup transformGroup;
	public Transform3D transform;
	public boolean isOn = false;
	private Alpha rotationAlpha;
	private BranchGroup masterGroup;
	private RotationInterpolator rotateInterpolator;
	
	public MyRotatingSystem(BranchGroup masterGroup, TransformGroup target)
	{
		
		target.setCapability(transformGroup.ALLOW_TRANSFORM_WRITE);
		
		transform = new Transform3D();
		transform.rotX(Math.PI/2.0);  
		
		rotationAlpha = 
		new Alpha(-1, 
				  16000);
		
	    rotateInterpolator =
		new RotationInterpolator(
								 rotationAlpha,
								 target, 
								 transform,
								 0.0f, 
								 (float) (Math.PI*2.0));
		

		masterGroup.addChild(rotateInterpolator); 

		this.masterGroup = masterGroup;

	}
	
	public void toggle()
	{
		
		if(isOn){
			rotationAlpha.pause();
		}
		else {
			rotationAlpha.resume();
		}
		
		isOn = !isOn;
	}

	public void start()
	{
		BoundingSphere bounds = new BoundingSphere();
		rotateInterpolator.setSchedulingBounds(bounds);
	}

}


	
	
	
