
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.Font;
import com.sun.j3d.utils.geometry.Text2D;

public class MyRotateCoords extends MySubBranch

{
	//private TransformGroup transformTarget;
	
	public MyRotateCoords(TransformGroup parent)//, TransformGroup transformTarget)
	{
		super((Group)parent);
		// this.transformTarget = transformTarget;

		// The following transform group is what is 
		// set up by the base class
		
		// We need to modify the transform Group Capabilities
		// so that the behavior object can modify it.
		parent.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		parent.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	
		Text2D text2D = new Text2D("Rotate with x,X, y,Y, z,Z",  
								   new Color3f(0.9f, 1.0f, 1.0f), 
								   "Helvetica",  
								   33,
								   Font.ITALIC);  
		transformGroup.addChild(text2D);
		
		// Here is where we pass the Transform Group to the behavior
		// that will manipulate it.
		BehaviorRotateByKey b = new BehaviorRotateByKey(parent);
		
		// The behavior must be added to the Scene Graph to be active
		transformGroup.addChild(b);
		
	}
	
}





