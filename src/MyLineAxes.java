
import javax.media.j3d.*;
import javax.vecmath.*;
import javax.media.j3d.LineArray;
import java.awt.Font;
import com.sun.j3d.utils.geometry.Text2D;	

public class MyLineAxes extends MySubBranch
{
	public MyLineAxes(TransformGroup parent)
	{
		super((Group)parent);
		
		int ticCount = 10;
		float ticSize = 0.1f;
		
		// X axis
		TransformGroup axesGroup = new TransformGroup();
		axesGroup.addChild(singleAxis(new Color3f(1.0f, 0.0f, 0.0f),	ticCount,ticSize));
		
		// -X axis
		Transform3D negXTransform  = new Transform3D();
		negXTransform.rotY(Math.PI);
		TransformGroup negXGroup = new TransformGroup(negXTransform);
		negXGroup.addChild(singleAxis(new Color3f(0.7f,0.0f, 0.0f),ticCount,ticSize));
		axesGroup.addChild(negXGroup);
		
		// X axis label
		Transform3D positionX = new Transform3D();
		positionX.setTranslation(new Vector3f(ticCount*ticSize,0.0f,0.0f));
		TransformGroup groupXLabel = new TransformGroup(positionX);
		Text2D text2D = new Text2D("X",new Color3f(0.9f, 0.0f, 0.0f), 
								   "Helvetica",33,Font.ITALIC);  
		groupXLabel.addChild(text2D);
		axesGroup.addChild(groupXLabel);
		
		// Y axis
		Transform3D posYTransform  = new Transform3D();
		posYTransform.rotZ(Math.PI/2.0);
		TransformGroup posYGroup = new TransformGroup(posYTransform);
		posYGroup.addChild(singleAxis(new Color3f(0.0f,1.0f, 0.0f),ticCount,ticSize));
		axesGroup.addChild(posYGroup);
		
		// -Y axis
		Transform3D negYTransform  = new Transform3D();
		negYTransform.rotZ(Math.PI* 3.0/2.0);
		TransformGroup negYGroup = new TransformGroup(negYTransform);
		negYGroup.addChild(singleAxis(new Color3f(0.0f,0.7f, 0.0f),ticCount,ticSize));
		axesGroup.addChild(negYGroup);
		
		// Y axis label
		Transform3D positionY = new Transform3D();
		positionY.setTranslation(new Vector3f(0.0f,ticCount*ticSize,0.0f));
		TransformGroup groupYLabel = new TransformGroup(positionY);
		Text2D text2Dy = new Text2D("Y",new Color3f(0.0f, 0.9f, 0.0f), 
								   "Helvetica",33,Font.ITALIC);  
		groupYLabel.addChild(text2Dy);
		axesGroup.addChild(groupYLabel);
		
		// Z axis
		Transform3D posZTransform  = new Transform3D();
		posZTransform.rotY(Math.PI*3.0/2.0);
		TransformGroup posZGroup = new TransformGroup(posZTransform);
		posZGroup.addChild(singleAxis(new Color3f(0.0f,0.7f, 1.0f),ticCount,ticSize));
		axesGroup.addChild(posZGroup);
		
		// -Z axis
		Transform3D negZTransform  = new Transform3D();
		negZTransform.rotY(Math.PI/ 2.0);
		TransformGroup negZGroup = new TransformGroup(negZTransform);
		negZGroup.addChild(singleAxis(new Color3f(0.0f,0.0f, 0.7f),ticCount,ticSize));
		axesGroup.addChild(negZGroup);
		
		
		
		transformGroup.addChild( axesGroup);
	}
	
	public static Shape3D singleAxis(Color3f color, int ticCount, float ticSize)
	{	
		int numPoints = 2 + ticCount*2;
		float hashSize = ticSize/3.0f;
		
		LineArray lines = new LineArray(numPoints,LineArray.COORDINATES
										|LineArray.COLOR_3);


		
		lines.setCoordinate(0,new Point3f(0.0f,0.0f,0.0f));
		lines.setCoordinate(1,new Point3f(ticSize*ticCount,0.0f,0.0f));
		lines.setColor(0,color);
		lines.setColor(1,color);
		
		for (int i=1;i<=ticCount;i++)
		{
			float x = i *ticSize;
			lines.setCoordinate(i*2,new Point3f(x,-hashSize,0.0f));
			lines.setCoordinate(i*2+1,new Point3f(x,hashSize,0.0f));
			lines.setColor(i*2,color);
			lines.setColor(i*2+1,color);
								 
		}

		LineAttributes att = new LineAttributes(1.0f,LineAttributes.PATTERN_SOLID,true);
		Appearance appear  = new Appearance();
		appear.setLineAttributes(att);
		
		Shape3D finalShape = new Shape3D(lines,appear);
		
		return finalShape;
	}
	

}


	
	
	
