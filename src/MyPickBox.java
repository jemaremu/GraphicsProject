
import javax.media.j3d.*;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public  class MyPickBox
{
	private TransformGroup transGroup;
	private Transform3D transform;
	private Shape3D shape;
	private Geometry geometry;
	private Appearance appearance;
	private float xSize, ySize,zSize;
	private float x,y,z;
	
	public  MyPickBox(
			float xSize, float ySize, float zSize,
			float x, float y, float z,
			Appearance app){
		
		this.xSize = xSize;
		this.ySize = ySize;
		this.zSize = zSize;
		this.x = x;
		this.y = y;
		this.z = z;
		
		transGroup = new TransformGroup();
		
		transform = new Transform3D();
		transform.setTranslation(new Vector3d(x,y,z));

		transGroup.setTransform(transform);

		shape = Box.createBoxShape(
						xSize,
						ySize, 
						zSize,app);
		
		shape.setUserData(this);
		
		transGroup.addChild(shape);
		transGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		geometry = shape.getGeometry();
		
		appearance = shape.getAppearance();
		
		shape.setCapability(Shape3D.ALLOW_LOCAL_TO_VWORLD_READ);
		shape.setCapability(Shape3D.ALLOW_GEOMETRY_READ);
	
		geometry.setCapability(GeometryArray.ALLOW_COORDINATE_READ);
		geometry.setCapability(GeometryArray.ALLOW_NORMAL_READ);
		geometry.setCapability(GeometryArray.ALLOW_COUNT_READ);
		geometry.setCapability(GeometryArray.ALLOW_FORMAT_READ);
	}
		
	public TransformGroup getBox()
	{
		return transGroup;
	}
	
	public void setTransform(Transform3D trans)
	{
		transGroup.setTransform(trans);
	}
	
	public Point3f getTop(){
		return  new Point3f(x+xSize, y+ySize, z+zSize);
	}
	
	public void stackOnBox(MyPickBox box2)
	{
		Point3f topOfFirst = box2.getTop();
		
		Transform3D trans = new Transform3D();
		trans.setTranslation(new Vector3f(topOfFirst));
		
		transGroup.setTransform(trans);
		
	}

}


	
	
	
