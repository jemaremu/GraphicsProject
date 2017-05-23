

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class MyProjections
{
	public TransformGroup transformGroup;
	SimpleUniverse simpleU;
	
	public MyProjections()
	{

	}
	public void setTransformGroup(TransformGroup group)
	{		
		transformGroup = group;
		group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	}
	
	public void setSimpleUniverse(SimpleUniverse u)
	{
		simpleU = u;
	}
	
	
	public  void setAngle(double horizAngle, double vertAngle)
	{
		Transform3D rotX = new Transform3D();
		rotX.rotX((-90+vertAngle)*Math.PI / 180.0);
		
		Transform3D rotZ = new Transform3D();
		rotZ.rotZ(horizAngle * Math.PI / 180.0);
		
		Transform3D rotZThenX = new Transform3D();
		rotZThenX.mul(rotX,rotZ);
		
		transformGroup.setTransform(rotZThenX);
	
	}

	public void isometric()
	{
		doParallel();
		double vertAngle = Math.atan(1.0/Math.sqrt(2.0));
		System.out.println(vertAngle);
		vertAngle *= 180.0/Math.PI;
		setAngle(-45.0, vertAngle);
	}
	public void dimetric()
	{
		doParallel();
		setAngle(-45.0, 60.0);
	}
	public void topView()
	{
		doParallel();
		Transform3D rot = new Transform3D();
		transformGroup.setTransform(rot);
	}
	public void bottomView()
	{
		doParallel();
		Transform3D rot = new Transform3D();
		rot.rotY(Math.PI );		
		transformGroup.setTransform(rot);
	}
	public void leftView()
	{
		doParallel();
		Transform3D rot = new Transform3D();
		rot.rotY(Math.PI / 2.0);		
		transformGroup.setTransform(rot);
	}
	public void rightView()
	{
		doParallel();
		Transform3D rot = new Transform3D();
		rot.rotY(-Math.PI / 2.0);		
		transformGroup.setTransform(rot);
	}
	public void frontView()
	{
		doParallel();
		Transform3D rot = new Transform3D();
		rot.rotX(-Math.PI / 2.0);		
		transformGroup.setTransform(rot);
	}
	public void doParallel()
	{
	
		simpleU.getViewer().getView().setProjectionPolicy(
			View.PARALLEL_PROJECTION);
	}
	
}


