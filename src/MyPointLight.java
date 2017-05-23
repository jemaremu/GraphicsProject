
import javax.media.j3d.*;
import javax.vecmath.*;

public class MyPointLight extends MySubBranch
{
	public MyPointLight(BranchGroup parent)
	{
		super((Group)parent);

		addPointLight(
				new Color3f(1.0f, 1.0f, 0.0f),
				new Point3f(-0.5f, -0.5f, 0.2f),
				new Point3f(0.0f,0.0f,2.0f));
		addPointLight(
				new Color3f(1.0f, 0.0f, 0.0f),
				new Point3f(0.5f, 0.5f, 0.2f),
				new Point3f(0.0f,0.0f,2.0f));
//		addPointLight(
//				new Color3f(0.5f, 0.9f, 0.9f),
//				new Point3f(0.5f, -0.5f, 0.5f),
//				new Point3f(0.0f,0.0f,2.0f));
	}
	
	private void addPointLight(Color3f color, Point3f position,Point3f attenuation)
	{

		PointLight light1 = new PointLight(color,position,attenuation);
		
		BoundingSphere bounds = new BoundingSphere(new Point3d(2.0,2.0,2.0),555.0);
		light1.setInfluencingBounds(bounds);
		
		transformGroup.addChild(light1);
		
		// Now draw a light bulb at the position of the light
		PointArray  singlePointArray = new PointArray(1,PointArray.COORDINATES|PointArray.COLOR_3);
		singlePointArray.setCoordinate(0, position);
		singlePointArray.setColor(0,color);
		
		PointAttributes att = new PointAttributes(12,false);
		Appearance app  = new Appearance();
		app.setPointAttributes(att);
		Shape3D lightBulb  = new Shape3D(singlePointArray,app);
		transformGroup.addChild(lightBulb);

		
	}	
}


	
	
	
