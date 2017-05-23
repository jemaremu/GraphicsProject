/* Course: CS 3370/Fall 2013
   modified by Jessica Rebollosa
   Assignment 8: Animation
   Instructor: Jesse Allen
   Date of last modification: Nov 24 2013
   			The coordinates of just the ant and not the entire scene 
 * */

import javax.media.j3d.*;
import javax.vecmath.*;

public class Panda extends MySubBranch {

	public Panda(TransformGroup parent) {
		super((Group)parent);
		transformGroup.addChild(CreateMovingPanda(createAnimal()));
		//transformGroup.addChild(createAnimal());
	}

	Color3f black = new Color3f(0.0f,0.0f,0.0f);
	Color3f white = new Color3f(1.0f,1.0f,1.0f);

	Appearance whiteAppearance;
	Appearance blackAppearance;
	
	float outterBody;
	float leftHand;
	float rigthHand;
	float leftLeg;
	float rigthLeg;
	float head;
	float leftEar;
	float rigthEar;
	float leftEye;
	float rigthEye;
	float nose;
	TransformGroup animalGroup;

	private void createAppearances() {	
		whiteAppearance = new Appearance();
		blackAppearance = new Appearance();

		fillAppearanceFromColor(whiteAppearance,white);
		fillAppearanceFromColor(blackAppearance,black);
	}


	private void fillAppearanceFromColor(Appearance app, Color3f color) {
		Material mat = new Material(
				color,//ambient
				black,//emissive
				color,//diffuse
				white,//specular
				128.0f//shininess
				);
		app.setMaterial(mat);
	}

	private void locateGroup(TransformGroup group, float x, float y, float z) {
		Transform3D changeTransform = new Transform3D();
		changeTransform.setTranslation(new Vector3f(x,y,z));

		Transform3D oldTransform = new Transform3D();
		group.getTransform(oldTransform);
		oldTransform.mul(changeTransform);

		group.setTransform(oldTransform);	
	}

	// Create branch that rotates everything below it -45 degrees about the Z axis
	private TransformGroup CreateMovingPanda(TransformGroup transPanda) {
		// We must be able to change the transform to do the animation
		transPanda.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		// Set Alpha configuration allowing for cyclic animation 
		// Plus acceleration and deceleration at each end
		Alpha riseAlpha = new Alpha(
				-1, // loopCount, forever
				Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE, // mode, 
				0,// triggerTime, 
				0,// phaseDelayDuration, 
				2000L,// increasingAlphaDuration, 
				2000L,// increasingAlphaRampDuration, 
				0L,// alphaAtOneDuration, 
				2000L,// decreasingAlphaDuration, 
				2000L,// decreasingAlphaRampDuration, 
				0L);// alphaAtZeroDuration) 

		// Create the Transformation that drives this translation
		// Translation will be along the x axis of this coordinate system
		Transform3D moveTransform = new Transform3D();

		// Apply the transformation and Alpha
		// along with the initial distance and final distance
		PositionInterpolator interp =
				new PositionInterpolator(
						riseAlpha, // Alpha controls from timeZhoumi
						transPanda, // Transform Group to animate
						moveTransform,// 3D Transform to use
						-0.9f, // Initial Position
						0.9f);// End Position

		// Set a bounding sphere identifying which elements will
		// be affected
		BoundingSphere bounds = new BoundingSphere();
		interp.setSchedulingBounds(bounds);

		// Now add this element to the Branch Group that
		// represents the main coordinate system of the objects
		transPanda.addChild(interp);

		return transPanda;
	}

	private TransformGroup CreateMovingLeg(TransformGroup transLegPanda, boolean left) {
		// We must be able to change the transform to do the animation
		transLegPanda.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		Alpha riseAlpha = new Alpha(
				-1, // loopCount, forever
				Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE, // mode, 
				0,// triggerTime, 
				0,// phaseDelayDuration, 
				20L,// increasingAlphaDuration, 
				20L,// increasingAlphaRampDuration, 
				500L,// alphaAtOneDuration, 
				20L,// decreasingAlphaDuration, 
				20L,// decreasingAlphaRampDuration, 
				500L);// alphaAtZeroDuration) 

		Transform3D axisOfTransform = new Transform3D();
		axisOfTransform.setRotation(new AxisAngle4d(1.0,1.0,0.0, Math.PI));

		PositionInterpolator riseInterpolator = null;
		if(left == true){
			riseInterpolator = new PositionInterpolator(
					riseAlpha, // Alpha controls from time
					transLegPanda, // Transform Group to animate
					axisOfTransform,// 3D Transform to use
					0.8f, // Initial Position
					0.9f);// End Position
		}	
		else{
			riseInterpolator = new PositionInterpolator(
					riseAlpha, // Alpha controls from time
					transLegPanda, // Transform Group to animate
					axisOfTransform,// 3D Transform to use
					0.9f, // Initial Position
					0.8f);// End Position
		}

		//		BoundingSphere bounds = new BoundingSphere();
		riseInterpolator.setSchedulingBounds(transLegPanda.getBounds());

		transLegPanda.addChild(riseInterpolator);
		return transLegPanda;
	}

	private TransformGroup createSphere(Appearance app, float x) {
		TransformGroup group = new TransformGroup();
		group.addChild(
				new com.sun.j3d.utils.geometry.Sphere(x,app));
		return group;
	}

	private void addPointLight(TransformGroup ant) {
		Color3f pointLightColor =
				new Color3f(1.0f,0.0f,0.0f);
		Point3f pointLightPosition =
				new Point3f(0.0f,0.95f,0.6f);
		Point3f pointLightAttenuation =
				new Point3f(0.0f,0.0f,2.0f);

		PointLight pointLight = new PointLight(
				pointLightColor,
				pointLightPosition,
				pointLightAttenuation);

		BoundingSphere boundingSphere = new BoundingSphere(new Point3d(2.0,2.0,2.0),555.0);
		pointLight.setInfluencingBounds(boundingSphere);

		ant.addChild(pointLight);
		
		
//		 FOR DEBUGGING Now draw a light bulb at the position of the light
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
	
	private TransformGroup createAnimal()
	{
		createAppearances();

		outterBody = 0.17f;
		
		leftHand = 0.07f;
		rigthHand = 0.07f;

		leftLeg = 0.09f;
		rigthLeg = 0.09f;

		head = 0.12f;

		leftEar = 0.05f;
		rigthEar = 0.05f;

		leftEye = 0.03f;
		rigthEye = 0.03f;

		nose = 0.02f;

		// THis is the group that will hold all of the pieces of the animal
		animalGroup = new TransformGroup();

		addPointLight(animalGroup);
		
		// For all parts, the createBox() method creates an object
		// and places it in a transform group
		// That transform group is what is returned
		// and needs to be modified to position the object
		// Realize that the objects are created with their center (not a corner) at the origin
		TransformGroup outterBodyRear = createSphere(whiteAppearance, outterBody);
		locateGroup(outterBodyRear, 0, 1.0f, 0.2f); //0.0+0.2=2.0
		animalGroup.addChild(outterBodyRear);

		//NEEDS TEXTURE NOT ANOTHER SPHERE!!!!!!
		//		TransformGroup innerBodyRear = createSphere(whiteAppearance, innerBody);
		//		moveGroup(innerBodyRear, 0, 0.1f, 0f);
		//		animalGroup.addChild(innerBodyRear);

		TransformGroup leftHandRear = createSphere(blackAppearance, leftHand);
		locateGroup(leftHandRear, -0.13f, 0.9f, 0.3f); //0.1 + 0.2 = 0.3
		animalGroup.addChild(leftHandRear);

		TransformGroup rigthHandRear = createSphere(blackAppearance, rigthHand);
		locateGroup(rigthHandRear, 0.13f, 0.9f, 0.3f); //0.1 + 0.2 = 0.3
		animalGroup.addChild(rigthHandRear);

		TransformGroup leftLegRear = createSphere(blackAppearance, leftLeg);
		//CreateMovingLeg(leftLegRear);
		locateGroup(leftLegRear, -0.13f, 1.0f, 0.13f); //-0.15 + 0.2 = 0.13
		//TransformGroup waggingTail  = createWaggingTail(tail);
		TransformGroup moveLeg = CreateMovingLeg(leftLegRear, true);
		TransformGroup positionedLeg = new TransformGroup();
		positionedLeg.addChild(moveLeg);
		locateGroup(positionedLeg, -0.13f, 0.1f, 0.09f); //-0.11 + 0.2 = 0.09
		animalGroup.addChild(positionedLeg);

		TransformGroup rigthLegRear = createSphere(blackAppearance, rigthLeg);
		locateGroup(rigthLegRear, 0.13f, 1.0f, 0.05f); //-0.15 + 0.2 = 0.05
		TransformGroup moveLegR = CreateMovingLeg(rigthLegRear, false);
		TransformGroup positionedLegR = new TransformGroup();
		positionedLegR.addChild(moveLegR);
		locateGroup(positionedLegR, 0.13f, 0.1f, 0.09f); //-0.11 + 0.2 = 0.09
		animalGroup.addChild(positionedLegR);
		//animalGroup.addChild(rigthLegRear);

		TransformGroup headRear = createSphere(whiteAppearance, head);
		locateGroup(headRear, 0.0f, (1.0f-(outterBody/4)), 0.37f); //0.17 + 0.2 = 0.37
		animalGroup.addChild(headRear);

		TransformGroup leftEarRear = createSphere(blackAppearance, leftEar);
		locateGroup(leftEarRear, -0.1f, (1.0f-(outterBody/4)), 0.44f); //0.24 + 0.2 = 0.44
		animalGroup.addChild(leftEarRear);

		TransformGroup rigthEarRear = createSphere(blackAppearance, rigthEar);
		locateGroup(rigthEarRear, 0.1f, (1.0f-(outterBody/4)), 0.44f); //0.24 + 0.2 = 0.44
		animalGroup.addChild(rigthEarRear);

		TransformGroup leftEyeRear = createSphere(blackAppearance, leftEye);
		locateGroup(leftEyeRear, -0.03f, (1.03f-(outterBody)), 0.43f); //0.21 + 0.2 = 0.43
		animalGroup.addChild(leftEyeRear);

		TransformGroup rigthEyeRear = createSphere(blackAppearance, rigthEye);
		locateGroup(rigthEyeRear, 0.03f, (1.03f-(outterBody)), 0.43f); //0.21 + 0.2 = 0.43
		animalGroup.addChild(rigthEyeRear);

		TransformGroup noseRear = createSphere(blackAppearance, nose);
		locateGroup(noseRear, 0.0f, (1.0f-(outterBody)), 0.37f); //0.17 + 0.2 = 0.37
		animalGroup.addChild(noseRear);

		return animalGroup;
	}
}
