package imageProcessing;

import java.awt.Color;
import java.awt.image.BufferedImage;

//-------------------------------------------
//Author: Séamus Ó Ceanainn
//-------------------------------------------


public class Transform {
	
/*	################################################################################
 *							Flipping Transformations 
 ################################################################################*/
	
	public static BufferedImage flipHorizontal(BufferedImage currImage){
		int width = currImage.getWidth();
		int height = currImage.getHeight();
		BufferedImage image = new BufferedImage(width, height, currImage.getType());
		int pv;
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				pv = currImage.getRGB(i, j);
				image.setRGB(width - 1 - i, j, pv);
			}
		return image;
	}
	
	public static BufferedImage flipVertical(BufferedImage currImage){
		int width = currImage.getWidth();
		int height = currImage.getHeight();
		BufferedImage image = new BufferedImage(width, height, currImage.getType());
		int pv;
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				pv = currImage.getRGB(i, j);
				image.setRGB(i, height - 1 - j, pv);
			}
		return image;
	}
	
/*	################################################################################
 *							Brightness Transformations 
 ################################################################################*/	
	
	public static BufferedImage brighten(BufferedImage currImage){
		int width = currImage.getWidth();
		int height = currImage.getHeight();
		BufferedImage image = new BufferedImage(width, height, currImage.getType());
		Color c;
		int pv;
		for (int i = 0; i < currImage.getWidth(); i++)
			for (int j = 0; j < currImage.getHeight(); j++) {
				pv = currImage.getRGB(i, j);
				c = new Color(pv);
				c = c.brighter();
				pv = c.getRGB();
				image.setRGB(i, j, pv);
			}
		return image;
	}
	
	public static BufferedImage darken(BufferedImage currImage){
		int width = currImage.getWidth();
		int height = currImage.getHeight();
		BufferedImage image = new BufferedImage(width, height, currImage.getType());
		Color c;
		int pv;
		for (int i = 0; i < currImage.getWidth(); i++)
			for (int j = 0; j < currImage.getHeight(); j++) {
				pv = currImage.getRGB(i, j);
				c = new Color(pv);
				c = c.darker();
				pv = c.getRGB();
				image.setRGB(i, j, pv);
			}
		return image;
	}	
	
/*	################################################################################
 *							Edges Transformations 
 ################################################################################*/
	
	public static BufferedImage edgesRed(BufferedImage currImage) {
		int width = currImage.getWidth();
		int height = currImage.getHeight();
		BufferedImage image = new BufferedImage(width, height, currImage.getType());
		Color c;
		int pv, red, reda;
		for (int i = 1; i < (currImage.getWidth() - 1); i++)
			for (int j = 1; j < (currImage.getHeight() - 1); j++) {
				pv = currImage.getRGB(i, j);
				c = new Color(pv);
				red = c.getRed();
				reda = 0;

				for (int k = i - 1; k <= i + 1; k++)
					for (int m = j - 1; m <= j + 1; m++) {
						pv = currImage.getRGB(k, m);
						c = new Color(pv);
						reda += c.getRed();
					}
				red = red - (reda / 9);
				if (red < 0)
					red = 0;
				red = red * 20;
				if (red > 255)
					red = 255;
				c = new Color(red, 0, 0);
				pv = c.getRGB();
				image.setRGB(i, j, pv);
			}
		return image;
	}
	
	public static BufferedImage edgesGreen(BufferedImage currImage) {
		int width = currImage.getWidth();
		int height = currImage.getHeight();
		BufferedImage image = new BufferedImage(width, height, currImage.getType());
		Color c;
		int pv, grn, grna;
		for (int i = 1; i < (currImage.getWidth() - 1); i++)
			for (int j = 1; j < (currImage.getHeight() - 1); j++) {
				pv = currImage.getRGB(i, j);
				c = new Color(pv);
				grn = c.getGreen();
				grna = 0;

				for (int k = i - 1; k <= i + 1; k++)
					for (int m = j - 1; m <= j + 1; m++) {
						pv = currImage.getRGB(k, m);
						c = new Color(pv);
						grna += c.getGreen();
					}
				grn = grn - (grna / 9);
				if (grn < 0)
					grn = 0;
				grn = grn * 20;
				if (grn > 255)
					grn = 255;
				c = new Color(0, grn, 0);
				pv = c.getRGB();
				image.setRGB(i, j, pv);
			}
		return image;
	}
	
	public static BufferedImage edgesBlue(BufferedImage currImage) {
		int width = currImage.getWidth();
		int height = currImage.getHeight();
		BufferedImage image = new BufferedImage(width, height, currImage.getType());
		Color c;
		int pv, blue, bluea;
		for (int i = 1; i < (currImage.getWidth() - 1); i++)
			for (int j = 1; j < (currImage.getHeight() - 1); j++) {
				pv = currImage.getRGB(i, j);
				c = new Color(pv);
				blue = c.getBlue();
				bluea = 0;

				for (int k = i - 1; k <= i + 1; k++)
					for (int m = j - 1; m <= j + 1; m++) {
						pv = currImage.getRGB(k, m);
						c = new Color(pv);
						bluea += c.getBlue();
					}
				blue = blue - (bluea / 9);
				if (blue < 0)
					blue = 0;
				blue = blue * 20;
				if (blue > 255)
					blue = 255;
				c = new Color(0, 0, blue);
				pv = c.getRGB();
				image.setRGB(i, j, pv);
			}
		return image;
	}
	
	public static BufferedImage edgesAll(BufferedImage currImage) {
		int width = currImage.getWidth();
		int height = currImage.getHeight();
		BufferedImage image = new BufferedImage(width, height, currImage.getType());
		Color c;
		int pv, blue, bluea, green, greena, red, reda;
		for (int i = 1; i < (currImage.getWidth() - 1); i++)
			for (int j = 1; j < (currImage.getHeight() - 1); j++) {
				pv = currImage.getRGB(i, j);
				c = new Color(pv);
				red = c.getRed();
				reda = 0;
				green = c.getGreen();
				greena = 0;
				blue = c.getBlue();
				bluea = 0;

				for (int k = i - 1; k <= i + 1; k++)
					for (int m = j - 1; m <= j + 1; m++) {
						pv = currImage.getRGB(k, m);
						c = new Color(pv);
						reda += c.getRed();
						greena += c.getGreen();
						bluea += c.getBlue();
					}
				red = red - (reda / 9);
				if (red < 0)
					red = 0;
				red = red * 20;
				if (red > 255)
					red = 255;

				green = green - (greena / 9);
				if (green < 0)
					green = 0;
				green = green * 20;
				if (green > 255)
					green = 255;

				blue = blue - (bluea / 9);
				if (blue < 0)
					blue = 0;
				blue = blue * 20;
				if (blue > 255)
					blue = 255;
				c = new Color(red, green, blue);
				pv = c.getRGB();
				image.setRGB(i, j, pv);
			}
		return image;
	}

}
