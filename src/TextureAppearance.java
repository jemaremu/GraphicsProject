
import javax.media.j3d.Appearance;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;

import com.sun.j3d.utils.image.TextureLoader;

public class TextureAppearance {

	public static Appearance create(String filename)
	{
		Color3f white = new Color3f(1.0f,1.0f,1.0f);

		Material m = new Material();
		m.setAmbientColor(white);
		m.setDiffuseColor(white);
		m.setSpecularColor(white);
		m.setShininess(128.0f);
		
		TextureAttributes tx = new TextureAttributes();
		tx.setTextureMode(TextureAttributes.MODULATE);
		
		Appearance a = new Appearance();
		a.setMaterial(m);
		a.setTexture(load2DTexture(filename));
		a.setTextureAttributes(tx);
		
		return a;
	}
	
	public static Texture2D load2DTexture(String filename)
	{
		String file;
		file = filename;
		
		TextureLoader loader = new TextureLoader(file, null);//mainDemo);
		ImageComponent2D image = loader.getImage();
		
		Texture2D texture = new Texture2D(Texture.BASE_LEVEL,Texture.RGB,
										  image.getWidth(),image.getHeight());
		
		
		texture.setImage(0,image);
		texture.setEnable(true);
		
		return texture;
		
	}
	
}
