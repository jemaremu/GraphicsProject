
import javax.media.j3d.*;
import javax.vecmath.*;

public class MyStandardAmbient extends MySubBranch
{
	public MyStandardAmbient(BranchGroup parent)
	{
		super((Group)parent);
		
		AmbientLight amb = new AmbientLight(new Color3f(0.5f,0.8f,1.0f));

		BoundingSphere bounds = new BoundingSphere(new Point3d(2.0,2.0,2.0),5.0);
		
		amb.setInfluencingBounds(bounds);
		
		transformGroup.addChild(amb);
	}	
}


	
	
	
