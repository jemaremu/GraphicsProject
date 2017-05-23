
import javax.media.j3d.*;
import javax.vecmath.*;

public class MyStandardDirectionalLight extends MySubBranch
{
	public MyStandardDirectionalLight(BranchGroup parent)
	{
		super((Group)parent);
		Color3f light1Color = new Color3f(1.0f, 0.9f, 0.8f);
		Vector3f light1Direction = new Vector3f(-1.0f, -1.0f, -1.0f);
		
		DirectionalLight light1 = new DirectionalLight(light1Color,light1Direction);
		
		BoundingSphere bounds = new BoundingSphere(new Point3d(1.0,1.0,1.0),500.0);
		
		light1.setInfluencingBounds(bounds);
		
		transformGroup.addChild(light1);
		
	}	
}


	
	
	
