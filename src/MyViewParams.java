
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public  class MyViewParams
{
	SimpleUniverse simpleU;
	
	public MyViewParams(SimpleUniverse u)
	{
		simpleU = u;
	}	
	
	public void doParallel()
	{
	
		simpleU.getViewer().getView().setProjectionPolicy(
			View.PARALLEL_PROJECTION);
	}
	
	public void doPerspective()
	{
		simpleU.getViewer().getView().setProjectionPolicy(
				View.PERSPECTIVE_PROJECTION);	
	}
	
	public void wideAngle()
	{
		double field  = simpleU.getViewer().getView().getFieldOfView();
		simpleU.getViewer().getView().setFieldOfView(field * 1.1);
	}
	
	public void narrowAngle()
	{
		double field  = simpleU.getViewer().getView().getFieldOfView();
		simpleU.getViewer().getView().setFieldOfView(field * 0.9);
	}
	
	private boolean antialiasOn = false;
	
	public void antialiasToggle()
	{
		antialiasOn = !antialiasOn;
		
		simpleU.getViewer().getView().setSceneAntialiasingEnable(antialiasOn);	
	}
	
}


