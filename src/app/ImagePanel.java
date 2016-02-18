package app;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

	@SuppressWarnings("serial")
	public class ImagePanel extends JPanel{

		private String imagePath;
	    private BufferedImage image;
	    
	    public ImagePanel(String aPath){
	    	imagePath = aPath;
	    	 try {
	    		  FileInputStream f = new FileInputStream(imagePath);
		          image = ImageIO.read(f);
		       } catch (IOException ex) {
		            // handle exception...
		       }
		    }
	    
	    public String imagePath(){
	    	return imagePath;
	    }
	    
	    public BufferedImage getImage(){
	    	return image;
	    }
	    
	    public BufferedImage resize(BufferedImage img, int newW, int newH) {  
	        int w = img.getWidth();  
	        int h = img.getHeight();  
	        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());  
	        Graphics2D g = dimg.createGraphics();  
	        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	        RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
	        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);  
	        g.dispose();
	        return dimg;  
	    } 

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        
	        //RESIZE THE IMAGE HERE
	        BufferedImage resizedImage = resize(image, 750, 750);
	        g.drawImage(resizedImage, 0, 0, null); 
	      }
	}
