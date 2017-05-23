/* credit to Instructor: Jesse Allen
 * modified by Adriana Carolina Camacho
 * last modified: 10/28/2013
 */
import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;


public class MyColors {

	public static Appearance flatWhite()
	{
		return FlatColor(new Color3f(0.7f,0.7f,0.7f));
	}	
	public static Appearance flatRed()
	{
		return FlatColor(new Color3f(0.7f,0.1f,0.1f));
	}
	public static Appearance flatBlue()
	{
		return FlatColor(new Color3f(0.1f,0.1f,0.7f));
	}	
	public static Appearance flatGreen()
	{
		return FlatColor(new Color3f(0.1f,0.7f,0.1f));
	}
	public static Appearance flatTan()
	{
		return FlatColor(new Color3f(1.0f,0.6f,0.3f));
	}
	public static Appearance brownish()
	{
		return FlatColor(new Color3f(0.5f,0.15f,0.07f));
	}
	public static Appearance black()
	{
		return FlatColor(new Color3f(0.0f,0.0f,0.0f));
	}
	
	public static Appearance FlatColor(Color3f	itemColor)
	{
		Appearance ap  = new Appearance();
		Color3f black = new Color3f(0.0f,0.0f,0.0f);
		ColoringAttributes ca = new ColoringAttributes(itemColor,ColoringAttributes.NICEST);
		ca.setShadeModel(ColoringAttributes.SHADE_FLAT);
		ap.setMaterial(new Material(itemColor,//ambient
									black,//emissive
									itemColor,//diffuse
									black,//specular
									1.0f));
		ap.setColoringAttributes(ca);
		return ap;
	}

}
