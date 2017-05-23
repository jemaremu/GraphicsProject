
import javax.media.j3d.Appearance;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TriangleArray;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;
import javax.vecmath.Vector3f;

public class Box {
	static Shape3D createBoxShape (float xSize,float ySize,float zSize,Appearance app)
	{
		int numFaces = 6;
		int numTriangles = numFaces * 2;
		int numPoints = numTriangles * 3;
		Point3f points[] = new Point3f[numPoints];
		Vector3f normals[] = new Vector3f[numPoints];
		TexCoord2f textureCoords[] = new TexCoord2f[numPoints];

		// points 1-4 go counterclockwise from xy origin at z=0
		Point3f p1 = new Point3f( 0.0f,  0.0f,  0.0f);
		Point3f p2 = new Point3f(xSize,  0.0f,  0.0f);
		Point3f p3 = new Point3f(xSize, ySize,  0.0f);
		Point3f p4 = new Point3f( 0.0f, ySize,  0.0f);
		// points 5-6 go counterclockwise from xy origin at max z
		Point3f p5 = new Point3f( 0.0f,  0.0f, zSize);
		Point3f p6 = new Point3f(xSize,  0.0f, zSize);
		Point3f p7 = new Point3f(xSize, ySize, zSize);
		Point3f p8 = new Point3f( 0.0f, ySize, zSize);

		// texture names match the multiple of 0.25 in u,v directions
		TexCoord2f t03 = new TexCoord2f(0.00f,0.75f);
		TexCoord2f t02 = new TexCoord2f(0.00f,0.50f);
		TexCoord2f t14 = new TexCoord2f(0.25f,1.00f);
		TexCoord2f t13 = new TexCoord2f(0.25f,0.75f);
		TexCoord2f t12 = new TexCoord2f(0.25f,0.50f);
		TexCoord2f t11 = new TexCoord2f(0.25f,0.25f);
		TexCoord2f t24 = new TexCoord2f(0.50f,1.00f);
		TexCoord2f t23 = new TexCoord2f(0.50f,0.75f);
		TexCoord2f t22 = new TexCoord2f(0.50f,0.50f);
		TexCoord2f t21 = new TexCoord2f(0.50f,0.25f);
		TexCoord2f t33 = new TexCoord2f(0.75f,0.75f);
		TexCoord2f t32 = new TexCoord2f(0.75f,0.50f);
		TexCoord2f t43= new TexCoord2f(1.00f,0.75f);
		TexCoord2f t42= new TexCoord2f(1.00f,0.50f);

		// Textual Descriptions are looking down z axis at the front towards x,y plane
		int next = 0;
		addQuadFace(p2,p1,p4,p3,Vectors.NEG_Z,t32,t42,t43,t33,points,normals,textureCoords,0);//BOTTOM
		addQuadFace(p5,p6,p7,p8,Vectors.POS_Z,t12,t22,t23,t13,points,normals,textureCoords,6*1); // TOP
		addQuadFace(p1,p2,p6,p5,Vectors.NEG_Y,t11,t21,t22,t12,points,normals,textureCoords,6*2); // FRONT
		addQuadFace(p6,p2,p3,p7,Vectors.POS_X,t22,t32,t33,t23,points,normals,textureCoords,6*3); // RIGHT
		addQuadFace(p8,p7,p3,p4,Vectors.POS_Y,t13,t23,t24,t14,points,normals,textureCoords,6*4); // BACK
		addQuadFace(p1,p5,p8,p4,Vectors.NEG_X,t02,t12,t13,t03,points,normals,textureCoords,6*5); // left

		TriangleArray tArray = new TriangleArray(numPoints,
				TriangleArray.COORDINATES | TriangleArray.NORMALS |TriangleArray.TEXTURE_COORDINATE_2);
		tArray.setCoordinates(0, points);
		tArray.setNormals(0,normals);
		tArray.setTextureCoordinates(0,0,textureCoords);

		Shape3D shape = new Shape3D(tArray,app);


		return shape;
	}

	public static void addQuadFace(
			Point3f p1,Point3f p2, Point3f p3,Point3f p4,
			Vector3f normal, 
			TexCoord2f t1, TexCoord2f t2, TexCoord2f t3, TexCoord2f t4,
			Point3f[] points,Vector3f[] normals, TexCoord2f[] textureCoords,
			int index)
	{	
		addTriangleFace(p1,p2,p3,normal,points,normals, index);
		addTriangleFace(p1,p3,p4,normal,points,normals, index+3);
		addTriangleTexture(t1,t2,t3,textureCoords, index);
		addTriangleTexture(t1,t3,t4,textureCoords, index+3);

	}

	public static void addTriangleFace(Point3f p1, Point3f p2, Point3f p3,
			Vector3f normal, Point3f[] points, Vector3f[] normals, int index)
	{
		points[index] = p1;
		points[index+1] = p2;
		points[index+2] = p3;
		normals[index] = normal;
		normals[index+1] = normal;
		normals[index+2] = normal;
	}

	public static void addTriangleTexture(TexCoord2f t1, TexCoord2f t2, TexCoord2f t3,
			TexCoord2f[] textureCoords,
			int index)
	{
		textureCoords[index] = t1;
		textureCoords[index+1] = t2;
		textureCoords[index+2] = t3;
	}
}
