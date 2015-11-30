import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.*;

public class cropImg{
	public static final int a4width = 3307;
	public static final int a4height = 4677;
	public static void main(String args[]) throws Exception{
		Image orig = ImageIO.read(new File("duke.jpg"));
		
		int partWidth = a4width/2;
		int partHeight = a4height/5;
		
		
		int x = 0, y = 2*partHeight, w = partWidth, h = partHeight;

		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		bi.getGraphics().drawImage(orig, 0, 0, w, h, x, y, x + w, y + h, null);
		ImageIO.write(bi, "png", new File("duke_cropped.png"));
	}
}