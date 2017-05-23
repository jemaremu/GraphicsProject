/* Course: CS 3370/Fall 2013
   modified by Adriana Carolina Camacho
   Assignment 8: Animation
   Instructor: Jesse Allen
   Date of last modification: Nov 24 2013
   			The coordinates of just the ant and not the entire scene 
 * */
import javax.media.j3d.*;
import javax.vecmath.*;

public class MyMovingAnt extends MySubBranch{

	TransformGroup ant;
	public MyMovingAnt(TransformGroup parent){
		super((Group)parent);

		TransformGroup tgScene = new TransformGroup();
		TransformGroup tgAnt = new TransformGroup();

		tgAnt.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		BehaviorControlAntWithKeys b = new BehaviorControlAntWithKeys(tgAnt);
		b.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0),100.0));
		tgScene.addChild(b);
		tgScene.addChild(tgAnt);

		createAnt(tgAnt);
		addPointLight(ant);

		transformGroup.addChild(tgScene);
	}

	public void createAnt(TransformGroup g, float scalenum, float anglePosition){
		Transform3D scale = new Transform3D();
		scale.setScale(0.003f); //smaller number smaller size		
			
		Transform3D rotateAnt = new Transform3D();
		rotateAnt.rotZ(90.0 * Math.PI/ 180.0);
		
		Transform3D positionAnt = new Transform3D();
		positionAnt.mul(scale, rotateAnt);
		
		transformGroup.setTransform(positionAnt);

		g.addChild(create(0.0,0.0,-90.0));
	}
	
	public void createAnt(TransformGroup g){
		Transform3D moveAnt = new Transform3D();
		moveAnt.setTranslation(new Vector3d(0.0,-320.0,0.0));
		
		Transform3D rotateAnt = new Transform3D();
		rotateAnt.rotZ(90.0 * Math.PI/ 180.0);
		
		Transform3D positionAnt = new Transform3D();
		positionAnt.mul(moveAnt, rotateAnt);
		
		Transform3D scale = new Transform3D();
		scale.setScale(0.003f); //smaller number smaller size
		
		Transform3D reSize = new Transform3D();
		reSize.mul(scale,positionAnt);
		
		transformGroup.setTransform(reSize);

		g.addChild(create(0.0,0.0,-90.0));
	}

	public TransformGroup create(double x, double y, double angle){

		Transform3D moveAnt = new Transform3D();
		moveAnt.setTranslation(new Vector3d(x,y,0.0));

		Transform3D rotateAnt = new Transform3D();
		rotateAnt.rotZ(angle * Math.PI/ 180.0);

		Transform3D positionAnt = new Transform3D();
		positionAnt.mul(moveAnt, rotateAnt);
		ant = new TransformGroup(positionAnt);		

		//addPointLight(ant);
		
		//ABDOMEN
		double abdX = 20;
		double abdY = 25;
		double abdZ = 17;
		TransformGroup abdomen = createBox(-3.5,-abdY,0.0,abdX,abdY,abdZ,MyColors.brownish());
		ant.addChild(abdomen);

		double center = 3.5;

		//THORAX
		double thorX = abdX - (center*2);
		double thorY = 15;
		double thorZ = abdZ - (center*2);	
		TransformGroup thorax = createBox(center/5,0.0,center,thorX,thorY,thorZ,MyColors.brownish());
		ant.addChild(thorax);

		//HEAD
		float radius = (float)abdY*0.51f;
		TransformGroup head1 = createSphere(center*2,thorY+(thorY/2),center*2,radius,MyColors.brownish());
		TransformGroup lefteye1 = createSphere(-3,7,3,(float)center*2,MyColors.flatWhite());
		lefteye1.addChild(createSphere(-.2,3,3,(float)center,MyColors.black()));//add pupil to left eye
		TransformGroup righteye1 = createSphere(+3,7,3,(float)center*2,MyColors.flatWhite());
		righteye1.addChild(createSphere(.2,3,3,(float)center,MyColors.black()));//add pupil to right eye
		head1.addChild(lefteye1);
		head1.addChild(righteye1);
		ant.addChild(head1); 		


		//LEGS
		double xL = thorX+center*1.5;
		double xR = thorX-center*4.5;
		double backPosition = (thorY/3)/2; //length of thorax divided by the 3 legs and then divided by 2 to center them
		double middlePosition = ((thorY/3)/2) + (thorY/3);
		double frontPosition = thorY - backPosition; 
		float length= (float) center*5;
		TransformGroup backLeftLeg = createAntLeg(true, xL,backPosition,center,1,length,MyColors.brownish());
		TransformGroup middleLeftLeg = createAntLeg(true, xL,middlePosition,center,1,length,MyColors.brownish());
		TransformGroup frontLeftLeg = createAntLeg(true, xL,frontPosition,center,1,length,MyColors.brownish());
		TransformGroup backRightLeg = createAntLeg(false, xR,backPosition,center,1,length,MyColors.brownish());
		TransformGroup middleRightLeg = createAntLeg(false, xR,middlePosition,center,1,length,MyColors.brownish());
		TransformGroup frontRightLeg = createAntLeg(false, xR,frontPosition,center,1,length,MyColors.brownish());
		ant.addChild(backLeftLeg); //back LEFT leg
		ant.addChild(middleLeftLeg); //middle LEFT leg
		ant.addChild(frontLeftLeg); //front LEFT leg
		ant.addChild(backRightLeg); //back RIGHT leg
		ant.addChild(middleRightLeg); //middle RIGHT leg
		ant.addChild(frontRightLeg); //front RIGHT leg

		//ANTENNAS		
		TransformGroup leftAntenna = createAntenas(center,thorY*2,center*2+radius,0.6f,length,MyColors.brownish());
		TransformGroup rightAntenna = createAntenas(center*4,thorY*2,center*2+radius,0.6f,length,MyColors.brownish()); 
		ant.addChild(leftAntenna);
		ant.addChild(rightAntenna);

		//MANDIBLES
		TransformGroup rightMandible = createCone(true,(float)center/2f,(float)center*2.5f,MyColors.brownish());
		moveGroup(rightMandible , 0.0f  , (float)center, 0.0f);
		TransformGroup rightBitting  = createBittingMandible(true,rightMandible);
		TransformGroup positionRight = new TransformGroup();
		positionRight.addChild(rightBitting);
		moveGroup(positionRight,(float)(center*2)+5f,((float)thorY*2.25f)-(float)center,(float)center/2);
		ant.addChild(positionRight);

		TransformGroup leftMandible = createCone(false,(float)center/2f,(float)center*2.5f,MyColors.brownish());
		moveGroup(leftMandible , 0.0f  , (float)center, 0.0f);
		TransformGroup leftBitting  = createBittingMandible(false, leftMandible);
		TransformGroup positionLeft = new TransformGroup();
		positionLeft.addChild(leftBitting);
		moveGroup(positionLeft,(float)(center*2)-5f,((float)thorY*2.25f)-(float)center,(float)center*.35f);
		ant.addChild(positionLeft);

		addPointLight(ant);

		return ant; 
	}

	private void addPointLight(TransformGroup ant){
		Color3f pointLightColor =
				new Color3f(0.85f,0.65f,0.13f);
		Point3f pointLightPosition =
				new Point3f(7.0f,0.0f,90.0f);//50.0
		Point3f pointLightAttenuation =
				new Point3f(0.00009f,0.00009f,0.00009f); //0.0001f,0.0001f,0.0002

		PointLight pointLight = new PointLight(
				pointLightColor,
				pointLightPosition,
				pointLightAttenuation);

		BoundingSphere boundingSphere = new BoundingSphere(new Point3d(2.0,2.0,2.0),555.0);
		pointLight.setInfluencingBounds(boundingSphere);

		ant.addChild(pointLight);
		
		
		// FOR DEBUGGING Now draw a light bulb at the position of the light
//		PointArray  singlePointArray = new PointArray(1,PointArray.COORDINATES|PointArray.COLOR_3);
//		singlePointArray.setCoordinate(0, pointLightPosition);
//		singlePointArray.setColor(0,pointLightColor);
//		
//		PointAttributes att = new PointAttributes(12,false);
//		Appearance app  = new Appearance();
//		app.setPointAttributes(att);
//		Shape3D lightBulb  = new Shape3D(singlePointArray,app);
//		ant.addChild(lightBulb);	
	}
	
	private TransformGroup createBittingMandible(boolean right, TransformGroup mandible) 
	{
		// This group serves two purposes
		//  - The transform group that is manipulated
		//  - The position in the tree where the interpolator is added
		//    (It must be in the tree to be active)
		TransformGroup bitTransformGroup = new TransformGroup();

		// We must be able to modify this transform
		bitTransformGroup.setCapability(mandible.ALLOW_TRANSFORM_WRITE);
		bitTransformGroup.addChild(mandible);

		// THe rotate interpolator rotates all objects about the 
		// x axis of a coordinate system
		// The following is the transform that positions
		// that coordinate system in the scene
		Transform3D transform = new Transform3D();
		transform.rotX(Math.PI/2);  

		Alpha rotationAlpha = new Alpha();

		rotationAlpha = 
				new Alpha(-1,// repeat 
						Alpha.DECREASING_ENABLE | Alpha.INCREASING_ENABLE,// mode
						0L,// triggerTime
						0L, // phaseDelayDuration
						500L, //increasingAlphaDuration
						500L, // increasingAlphaRampDuration, 
						0L, // alphaAtOneDuration, 
						500L, // decreasingAlphaDuration, 
						500L, //decreasingAlphaRampDuration, 
						0L // alphaAtZeroDuration
						);
		RotationInterpolator rotateInterpolator;
		if(right){
			rotateInterpolator =
					new RotationInterpolator(
							rotationAlpha,    // The alpha object that will drive this interpolator
							bitTransformGroup,   // The transform group that will be updated
							transform,  // The transform that positions the Y axis
							(float) (-Math.PI*0.1), // start value when mandible is of the RIGHT side
							(float) (Math.PI*0.1)); // end value
		}
		else{
			rotateInterpolator =
					new RotationInterpolator(
							rotationAlpha,    // The alpha object that will drive this interpolator
							bitTransformGroup,   // The transform group that will be updated
							transform,  // The transform that positions the Y axis
							(float) (Math.PI*0.1), // start value when mandible is of the LEFT side 
							(float) (-Math.PI*0.1)); // end value			
		}

		// We have to set the scheduling bounds so that this interpolator will be active
		BoundingSphere bounds = new BoundingSphere();
		rotateInterpolator.setSchedulingBounds(bounds);

		// This attaches the interpolator to the tree so that it will be active.
		bitTransformGroup.addChild(rotateInterpolator); 

		return bitTransformGroup;
	}

	public TransformGroup createBox(double x, double y, double z, double xDim, double yDim, double zDim, Appearance app)
	{
		// This was designed to create a box going from P1 to P2
		// It assumes x2>x1,y2>y1,z2>z1
		float xNew = (float)(x + xDim/2.0); 
		float yNew = (float)(y + yDim/2.0); 
		float zNew = (float)(z + zDim/2.0); 
		float xDel = (float)(xDim/2.0);
		float yDel = (float)(yDim/2.0);
		float zDel = (float)(zDim/2.0);
		Transform3D trans = new Transform3D();
		trans.setTranslation(new Vector3d(xNew,yNew,zNew)); //custom position 
		TransformGroup boxGroup = new TransformGroup(trans);
		boxGroup.addChild( new com.sun.j3d.utils.geometry.Box(xDel,yDel,zDel,app));

		return boxGroup;
	}

	public TransformGroup createSphere(double x, double y, double z,float radius, Appearance app){

		Transform3D move = new Transform3D();
		move.setTranslation(new Vector3d(x, y, z)); //custom position 
		TransformGroup sphereGroup = new TransformGroup(move);
		sphereGroup.addChild(new com.sun.j3d.utils.geometry.Sphere(radius, app));
		return sphereGroup;		
	}

	public TransformGroup createAntLeg(boolean left, double x, double y, double z, float radius, float height, Appearance app)
	{
		Transform3D diagonal = new Transform3D();
		if (left){		
			diagonal.rotY((Math.PI/2.0)/2); //45 degrees 
		}else{
			diagonal.rotY(-((Math.PI/2.0)/2)); //45 degrees 
		}	

		Transform3D side = new Transform3D();
		side.rotZ(Math.PI/2);			//90 degrees 

		Transform3D compound1 = new Transform3D();
		compound1.mul(diagonal, side); //first turn it to the side in order to be diagonal

		Transform3D trans = new Transform3D();
		trans.setTranslation(new Vector3d(x,y,z)); //custom position 

		Transform3D compound2 = new Transform3D();
		compound2.mul(trans, compound1);//join the translation with the rotations 

		TransformGroup cylGroup = new TransformGroup(compound2); //create leg according to transforms
		cylGroup.addChild(new com.sun.j3d.utils.geometry.Cylinder(radius,height,app));
		return cylGroup;
	}

	public TransformGroup createAntenas(double x, double y, double z, float radius, float height, Appearance app)
	{
		Transform3D diagonal = new Transform3D();	
		diagonal.rotX((Math.PI/2.0)/2); //45 degrees 

		Transform3D trans = new Transform3D();
		trans.setTranslation(new Vector3d(x,y,z)); //custom position 

		Transform3D compound2 = new Transform3D();
		compound2.mul(trans, diagonal);//join the translation with the rotations 

		TransformGroup cylGroup = new TransformGroup(compound2); //create leg according to transforms
		cylGroup.addChild(new com.sun.j3d.utils.geometry.Cylinder(radius,height,app));
		return cylGroup;
	}

	//CREATE CONE WITH FIXED POSITION NOT USED
	public TransformGroup createCone(boolean right, double x, double y, double z, float radius, float height, Appearance app)
	{
		//depending weather it is the left or right mandible
		Transform3D rot  = new Transform3D();
		if(right){
			rot.rotZ(Math.PI/6);
		}else{
			rot.rotZ(-(Math.PI/6));
		}

		Transform3D incline = new Transform3D();
		incline.rotX(-Math.PI/6);

		Transform3D compound1 = new Transform3D(); //union the two rotations to incline and to turn towards the middle depending on left or right
		compound1.mul(incline, rot);

		Transform3D trans = new Transform3D();
		trans.setTranslation(new Vector3d(x,y,z));

		Transform3D compound = new Transform3D(); //union the translation with the inclined and turns 
		compound.mul(trans, compound1);

		TransformGroup coneGroup = new TransformGroup(compound);
		coneGroup.addChild(new com.sun.j3d.utils.geometry.Cone(radius, height, app));
		return coneGroup;
	}

	public TransformGroup createCone(boolean right, float radius, float height, Appearance app)
	{
		//depending weather it is the left or right mandible
		Transform3D rot  = new Transform3D();
		if(right){
			rot.rotZ(Math.PI/6);
		}else{
			rot.rotZ(-(Math.PI/6));
		}

		Transform3D incline = new Transform3D();
		incline.rotX(-Math.PI/6);

		Transform3D compound1 = new Transform3D(); //union the two rotations to incline and to turn towards the middle depending on left or right
		compound1.mul(incline, rot);

		TransformGroup coneGroup = new TransformGroup(compound1);
		coneGroup.addChild(new com.sun.j3d.utils.geometry.Cone(radius, height, app));
		return coneGroup;
	}

	private void  moveGroup(TransformGroup group, float x, float y, float z)
	{
		Transform3D changeTransform = new Transform3D();
		changeTransform.setTranslation(new Vector3f(x,y,z));

		Transform3D oldTransform = new Transform3D();
		group.getTransform(oldTransform);
		oldTransform.mul(changeTransform);

		group.setTransform(oldTransform);	
	}

}