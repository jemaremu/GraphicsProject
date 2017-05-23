/* Course: CS 3370/Fall 2013
   modified by Adriana Carolina Camacho and Jessica Rebollosa
   Assignment 8: Animation
   Instructor: Jesse Allen
   Date of last modification: Nov 24 2013
   			The coordinates of just the ant and not the entire scene 
 * */

import java.awt.GraphicsConfiguration;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.sun.j3d.utils.image.*;

public class ComplexMain extends Frame  {
	private static final long serialVersionUID = 1L;
	// The following are parts of the tree that we need access to
	protected BranchGroup topBranch;
	protected Canvas3D canvas;
	protected TransformGroup projectionGroup;
	protected TransformGroup rotatingGroup;
	public TransformGroup masterGroup;
	protected MyStandardAmbient standardAmbient;
	protected MyStandardDirectionalLight standardDirectionalLight;
	protected MyPointLight pointLight;
	protected MyBlocks blocks;
	protected TransformGroup viewTransformGroup;
	protected MyProjections projections = new MyProjections();
	protected MyRotatingSystem rotatingSystem ;
	protected MyViewParams viewParams;
	protected MySceneGraph sceneGraph;
	protected MyRotateCoords rotateCoords;

	protected SimpleUniverse simpleU;
	protected boolean showOther = false;
	protected MyPointLight standardPointLight;

	public ComplexMain() {
		//Close the program
		addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						System.exit(0);
					}//end windowClosing
				}//end new WindowAdapter
				);//end addWindowListener

		new Menus(this);

		GraphicsConfiguration config = 
				SimpleUniverse.getPreferredConfiguration();

		canvas = new Canvas3D(config);
		this.add("Center", canvas);

		Locale locale = CreateSimpleU(canvas);
		// Make sure we can modify the tree
		topBranch = new BranchGroup();
		topBranch.setCapability(Group.ALLOW_CHILDREN_EXTEND);
		topBranch.setCapability(Group.ALLOW_CHILDREN_READ);
		topBranch.setCapability(Group.ALLOW_CHILDREN_WRITE);
		topBranch.setCapability(Group.ENABLE_PICK_REPORTING);
		topBranch.setCapability(Group.ALLOW_PICKABLE_READ);
		topBranch.setCapability(Group.ALLOW_PICKABLE_WRITE);

		// Add a transformGroup that will manage projections (iso, top, right, left, ...)
		projectionGroup = new TransformGroup();
		projectionGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		projectionGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		projectionGroup.setCapability(Group.ALLOW_CHILDREN_READ);
		projectionGroup.setCapability(Group.ALLOW_CHILDREN_WRITE);
		projectionGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);

		topBranch.addChild(projectionGroup);
		// Send this projection to the object that will manipulate it.
		projections.setTransformGroup(projectionGroup);
		projections.isometric();

		// Add a transformGroup below that that will animate rotation of the model
		rotatingGroup = new TransformGroup();
		projectionGroup.addChild(rotatingGroup);

		// Create the master Coordinate system and all 
		// the components that will be placed in it
		// Create the master Transform Group to which all 
		// components will be added
		masterGroup = new TransformGroup();

		// configure this group so that items can be added
		// and removed over time.
		masterGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
		masterGroup.setCapability(Group.ALLOW_CHILDREN_READ);
		masterGroup.setCapability(Group.ALLOW_CHILDREN_WRITE);

		rotatingGroup.addChild(masterGroup);

		// Create all of the objects that will respond to menu functions
		createAllComponents(masterGroup);

		//topBranch.addChild(projectionGroup);

		// Add this all to the top of the tree
		locale.addBranchGraph(topBranch);
		rotatingSystem.start();
		//projections.rightView();
	}

	public Locale CreateSimpleU(Canvas3D canvas) {
		SimpleUniverse simpleU = new SimpleUniverse(canvas);

		viewParams = new MyViewParams(simpleU);
		projections.setSimpleUniverse(simpleU);

		simpleU.getViewingPlatform().setNominalViewingTransform();

		ViewPlatform vp = simpleU.getViewer().getView().getViewPlatform();

		return simpleU.getLocale();
	} 

	//	MyLineAxes axes;
	Panda myPanda;
	MyMovingAnt moveAnt;

	public void createAllComponents(TransformGroup masterGroup) {
		// All components are based on a class "MySubBranch"
		// whose purpose is to attach/unattach the 
		// object to the master group.
		TextureLoader myLoader = new TextureLoader( "pandaHabitat.png", this );
		ImageComponent2D myImage = myLoader.getImage( );

		Background background = new Background(new Color3f(0,1f,1f));
		background.setImage( myImage );
		background.setImageScaleMode(background.SCALE_FIT_ALL);

		BoundingSphere sphere = new BoundingSphere(new Point3d(0,0,0), 100000);
		background.setApplicationBounds(sphere);
		topBranch.addChild(background);

		standardAmbient = new MyStandardAmbient(topBranch);
		standardAmbient.toggle();
		standardDirectionalLight = new MyStandardDirectionalLight(topBranch);
		standardDirectionalLight.toggle();
		//Platform
		MyBlocks base = new MyBlocks(masterGroup);
		base.toggle();
		
		moveAnt = new MyMovingAnt(masterGroup);
		moveAnt.toggle();	
		
		myPanda = new Panda(masterGroup);
		myPanda.toggle();
		rotateCoords = new MyRotateCoords(projectionGroup);
		rotatingSystem = new MyRotatingSystem(topBranch,rotatingGroup);
		rotatingSystem.toggle();
		sceneGraph = new MySceneGraph(masterGroup);
	}

	public static void main(String[] args) {
		ComplexMain demo = new ComplexMain();
		demo.setSize(600,600);
		demo.setVisible(true);
	}
}





