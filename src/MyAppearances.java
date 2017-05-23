
import javax.media.j3d.*;
import javax.vecmath.*;

public class MyAppearances
{
	public static Appearance flatWhite()
	{
		return FlatColor(new Color3f(0.7f,0.7f,0.7f));
	}
	
	public static Appearance flatRed()
	{
		return FlatColor(new Color3f(1.0f,0.6f,0.6f));
	}
	
	public static Appearance flatBlue()
	{
		return FlatColor(new Color3f(0.6f,0.6f,1.0f));
	}
	
	public static Appearance flatGreen()
	{
		return FlatColor(new Color3f(0.6f,1.0f,0.6f));
	}
	

	public static Appearance FlatColor(Color3f	itemColor)
	{
		Color3f black = new Color3f(0.0f,0.0f,0.0f);

		Appearance ap  = new Appearance();

		ap.setMaterial(new Material(
				itemColor,//ambient
				black,//emissive
				itemColor,//diffuse
				itemColor,//specular
				128.0f));

		ColoringAttributes ca = new ColoringAttributes(itemColor,ColoringAttributes.NICEST);
		ca.setShadeModel(ColoringAttributes.SHADE_GOURAUD);
		ap.setColoringAttributes(ca);
		
		return ap;
	}

	public static Appearance flatSurfaceWhite()
	{
		return SurfaceAppearance(new Color3f(1.0f,0.3f,0.3f));
	}

	public static Appearance SurfaceAppearance(Color3f	itemColor)
	{
		Appearance ap  = new Appearance();
		Color3f black = new Color3f(0.0f,0.0f,0.0f);
		ColoringAttributes ca = new ColoringAttributes(itemColor,ColoringAttributes.NICEST);
		ca.setShadeModel(ColoringAttributes.SHADE_GOURAUD);
		ap.setMaterial(new Material(
				itemColor,//ambient
				black,//emissive
				itemColor,//diffuse
				black,//specular
				1.0f));
		ap.setColoringAttributes(ca);
		return ap;
	}

}


	
	
	
