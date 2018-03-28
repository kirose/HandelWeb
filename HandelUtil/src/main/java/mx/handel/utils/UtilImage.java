package mx.handel.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UtilImage {
	public static final int FIT_HEIGHT = 1;
	public static final int FIT_WIFTH = 2;
	public static final int FIT_BOTH = 3;
	
	/**
	 * Resize an image
	 * @param imgFile
	 * @param height
	 * @param width
	 * @param typeFit
	 * @return
	 * @throws IOException
	 */
    public static void resizeImage(File img, File imgResized, String ext, int height, int width, int typeFit) throws IOException{
    	if (width <= 0){
    		throw new RuntimeException("El ancho especificado para redimensionalizar la imagen es invalido: "+width);
    	}
    	if (height <= 0){
    		throw new RuntimeException("El alto especificado para redimensionalizar la imagen es invalido: "+height);
    	}
    	if (!img.exists() && !img.isFile()){
    		throw new IOException("No existe la imagen que intenta redimensionar: "+img.getAbsolutePath()); 
    	}
    	BufferedImage buferedImg = ImageIO.read(img);
    	int type = buferedImg.getType() == 0? BufferedImage.TYPE_INT_ARGB : buferedImg.getType();//BufferedImage.TYPE_INT_ARGB;//
    	int x = 0, y = 0, w = 0, h = 0;
    	int propWidth = buferedImg.getWidth() * height / buferedImg.getHeight();
    	int propHeight = buferedImg.getHeight() * width / buferedImg.getWidth();
    	if (typeFit == FIT_BOTH){
    		typeFit = Double.valueOf(buferedImg.getWidth()) / Double.valueOf(buferedImg.getHeight()) >= Double.valueOf(width) / Double.valueOf(height) ? FIT_WIFTH : FIT_HEIGHT; 
    	}
    	if (typeFit == FIT_HEIGHT){
    		y = 0;
    		x = width > propWidth ? (width - propWidth)/2 : 0;
    		h = height;
    		w = Math.min(width,propWidth);
    	} else if (typeFit == FIT_WIFTH){
    		y = height > propHeight ? (height - propHeight)/2 : 0;
    		x = 0;
    		h = Math.min(height,propHeight);
    		w = width;
    	}
		BufferedImage bufferedImgResized = new BufferedImage(width, height, type);
		Graphics2D g = bufferedImgResized.createGraphics();
		
		//g.fillRect(x, y, propWidth, propHeight);
		//g.setColor(c);
		//g.setRenderingHints(hints);
		//g.fillRect(0, 0, width, height);
		//g.setComposite(AlphaComposite.Clear);
		//g.setColor(new Color(0, 0, 0, 0));
		//g.setComposite(AlphaComposite.Clear);
		//g.setComposite(AlphaComposite.Src);
		
		g.fillRect(0, 0, width, height);
		g.drawImage(buferedImg, x, y, w, h, null);
		g.setComposite(AlphaComposite.Clear);
		g.dispose();
		ImageIO.write(bufferedImgResized,ext,imgResized);
    }
}
