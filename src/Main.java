/* Course: CS 3370/Fall 2013
   modified by Adriana Carolina Camacho and Jessica Rebollosa
   Assignment 8: Animation
   Instructor: Jesse Allen
   Date of last modification: Nov 24 2013
   			Main used to debug
 * */

import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;


public class Main extends Frame  {
	SimpleUniverse simpleU;
	Canvas3D canvas;
	boolean showOther = false;
	MyStandardAmbient standardAmbient;
	MyStandardDirectionalLight standardDirectionalLight;
	MyPointLight standardPointLight;
		
	public Main() {
		// Set up Canvas
		GraphicsConfiguration config = 
			SimpleUniverse.getPreferredConfiguration();
		canvas = new Canvas3D(config);
			
		add("Center", canvas);
	
		// Set up Simple Universe
		simpleU = new SimpleUniverse(canvas);
		
		simpleU.getViewingPlatform().setNominalViewingTransform();
		
		BranchGroup scene = createSceneGraph();	
		
		scene.setCapability(Group.ALLOW_CHILDREN_EXTEND);
		scene.setCapability(Group.ALLOW_CHILDREN_READ);
		scene.setCapability(Group.ALLOW_CHILDREN_WRITE);
		
		standardAmbient = new MyStandardAmbient(scene);
		standardAmbient.toggle();
		
		standardDirectionalLight = new MyStandardDirectionalLight(scene);
		standardDirectionalLight.toggle();
		
		standardPointLight = new MyPointLight(scene);
		standardPointLight.toggle();
		
		TextureLoader myLoader = new TextureLoader( "pandaHabitat.png", this );
		ImageComponent2D myImage = myLoader.getImage( );
		
		Background background = new Background(new Color3f(0,1f,1f));
		background.setImage( myImage );
		background.setImageScaleMode(background.SCALE_FIT_ALL);
		
		BoundingSphere sphere = new BoundingSphere(new Point3d(0,0,0), 100000);
		background.setApplicationBounds(sphere);
		scene.addChild(background);
		
		simpleU.addBranchGraph(scene);
	} 
	
	public BranchGroup createSceneGraph()
	{
		// Create Highest level branchgroup
		BranchGroup objRoot = new BranchGroup();
		
		// Set a transform below the top to give us a good view
		TransformGroup myView = SimpleView();
		objRoot.addChild(myView);
				
		// Add everything else to that view
		myView.addChild( createCompoundModel());

		return objRoot;
	}
	
	public TransformGroup SimpleView()
	{		
		Transform3D rotX = new Transform3D();
		rotX.rotX(-3.14/4.0);
		
		Transform3D rotZ = new Transform3D();
		rotZ.rotZ(-3.14/4.0);
		
		Transform3D rotZThenX = new Transform3D();
		rotZThenX.mul(rotX,rotZ);
		
		TransformGroup transGroup = new TransformGroup(rotZThenX);
				
		return transGroup;
	}
	

	MyLineAxes axes;
	Panda myPanda;
	MyMovingAnt moveAnt;
//	private MyRotatingSystem rotatingSystem;
//	private MyRotateCoords rotateCoords;
	
	public TransformGroup createCompoundModel()
	{

		TransformGroup compoundGroup = new TransformGroup();
		
		axes = new MyLineAxes(compoundGroup);
		axes.toggle();
		
//		base = new MyBasePlate(compoundGroup);
//		base.toggle();
		
		MyBlocks base = new MyBlocks(compoundGroup);
		base.toggle();
		
		myPanda = new Panda(compoundGroup);
		myPanda.toggle();
		
		moveAnt = new MyMovingAnt(compoundGroup);
		moveAnt.toggle();
		
		
//		rotateCoords = new MyRotateCoords(compoundGroup);
//		rotatingSystem = new MyRotatingSystem(compoundGroup,rotatingGroup);
		
		
		
		return compoundGroup;
	}
	
	
	
	public static void main(String[] args)
	{
		Main demo = new Main();
		demo.setSize(600,600);
		demo.setVisible(true);
	}
}


	
	
	
