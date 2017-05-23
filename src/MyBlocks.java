
import javax.media.j3d.*;
import javax.vecmath.*;

public  class MyBlocks extends MySubBranch
{
	public MyBlocks(TransformGroup parent)
	{
		super((Group)parent);
		
		MyPickBox block1 = new MyPickBox(2.4f,2.4f,0.2f,-1.2f,-1.2f,-0.2f,TextureAppearance.create("Grass.jpg"));
		transformGroup.addChild(block1.getBox());
	}	
}


	
	
	
