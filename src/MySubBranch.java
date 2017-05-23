
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;

// Base class for objects to be added to Java 3D scenegraph
// - branch group can be added/removed
// - transform group allows modification
// - Object can be turned on/off by adding to/removing from tree

public abstract class MySubBranch
{
	private boolean displayOn = false;
	protected Group parent;
	private BranchGroup branch;
	public TransformGroup transformGroup;
		
	public MySubBranch(Group parent)
	{
		//System.out.println("SubBranchCreate");
		this.parent = parent;
		branch = new BranchGroup();
		branch.setCapability(BranchGroup.ALLOW_DETACH);
		
		transformGroup = new TransformGroup();

		branch.addChild(transformGroup);
	}
	
	public void toggle()
	{
		//System.out.println("toggled");
		displayOn = !displayOn;
		reparent();
	}
	

	private void reparent()
	{
		int index =		parent.indexOfChild(branch);

		if (displayOn)
		{
			if (index == -1)
			{
				parent.addChild(branch);
			}
		}
		else 
		{
			if (index>=0)
			{
				parent.removeChild(index);
			}
			
		}

	}
	
}


	
	
	
