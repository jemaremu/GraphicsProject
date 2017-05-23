
import java.util.Enumeration;

import javax.media.j3d.Group;
import javax.media.j3d.SceneGraphObject;
import javax.media.j3d.TransformGroup;

// UNDER DEVELOPMENT
public class MySceneGraph extends MySubBranch
{
	public MySceneGraph(TransformGroup parent)
	{
		super((Group)parent);
	}
	public void printHierarchy()
	{
		System.out.println("Scene Graph Analysis");
		DisplayNode("", (SceneGraphObject) parent);
		
	}
	
	public void DisplayNode(String indent,SceneGraphObject node)
	{
		String c = node.getClass().toString();
		
		System.out.println(indent + c);
		if (c == "class javax.media.j3d.TransformGroup")
		{
			Enumeration children = ((Group)node).getAllChildren();
			while (children.hasMoreElements())
			{ 
				System.out.println("xx");
				children.nextElement();
				
			}
		}
		
	}
}
