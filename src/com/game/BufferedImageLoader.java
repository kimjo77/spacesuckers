package com.game;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader{
	
	private BufferedImage image;

	public BufferedImage loadImage(String path) {	
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	public BufferedImage loadImage(String path, double scaleX, double scaleY, int type) {
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage scaledImage = new BufferedImage((int)(width*scaleX),(int) (height*scaleY), BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(scaleX,scaleY);
		AffineTransformOp scaleOp = null;
		switch(type) {
		case 0: scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR); break;
		case 1: scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR); break;
		case 2: scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC); break;
		}
		scaledImage = scaleOp.filter(image, scaledImage);
		
		return scaledImage;
	}
}
