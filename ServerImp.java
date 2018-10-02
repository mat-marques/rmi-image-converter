//import java.rmi.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.util.*;


public class ServerImp {
	
	/*
	 * Função para converter uma imagem para tons de cinza
	 */
	//@Override
	public static BufferedImage grayscaleConvertion(BufferedImage image){
		BufferedImage source = null;
		System.out.println("Inicio da conversão da imagem recebida.");
		
		int width = image.getWidth();
		int height = image.getHeight();
		source = new BufferedImage(width, height, image.getType());
		
		for(int y = 0; y < height; y++){
		      for(int x = 0; x < width; x++){
		        int p = image.getRGB(x,y);

		        int a = (p>>24)&0xff;
		        int r = (p>>16)&0xff;
		        int g = (p>>8)&0xff;
		        int b = p&0xff;

		        //calculate average
		        int avg = (r+g+b)/3;

		        //replace RGB value with avg
		        p = (a<<24) | (avg<<16) | (avg<<8) | avg;

		        source.setRGB(x, y, p);
		      }
		}
		
		System.out.println("Fim da conversão da imagem.");
		return source;
	}
	
	public static ArrayList<BufferedImage> splitImage() {
		List<BufferedImage> listSubImage = new ArrayList<BufferedImage>();
		
	    try {
	    	
			final BufferedImage source = ImageIO.read(new File("Images/imagen4k.jpg"));
			int idx = 0;
			
			
			for (int y = 0; y < source.getHeight(); y += 32) {
				listSubImage.add(source.getSubimage(0, y, 32, 32));
				//ImageIO.write(source.getSubimage(0, y, 32, 32), "jpg", new File("Images/split_" + idx++ + ".jpg"));
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return (ArrayList<BufferedImage>) listSubImage;
	}
	
	/*
	 * Função para registrar um endereço para o servidor
	 */
	//@SuppressWarnings("deprecation")
	public static void main(String [] args) {
		
//			ArrayList<BufferedImage> listSubImage;
//			
//			listSubImage = splitImage();
			BufferedImage source;
			try {
				source = ImageIO.read(new File("Images/imagen4k.jpg"));
				ImageIO.write(grayscaleConvertion(source), "jpg", new File("Images/grayscaleImage.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			try {
//				for (int y = 0; y < listSubImage.size(); y++) {
//					ImageIO.write(grayscaleConvertion(listSubImage.get(y)), "jpg", new File("Images/split_"+ y +".jpg"));
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
			System.out.println("Fim.");
			
//		    // install RMI security manager
//		    System.setSecurityManager(new RMISecurityManager());
//		    // arg. 0 = rmi url
//		    if (args.length!=1) {
//		      System.err.println("Usage: TicketServerImpl <server-rmi-url>");
//		      System.exit(-1);
//		    }
//		    try {  
//		      // name with which we can find it = user name
//		      String name = args[0];
//		      
//		      //create new instance
//		      ServerImp server = new ServerImp();
//		      
//		      // register with nameserver
//		      Naming.rebind(name, server);
//		      System.out.println("Started Server, registered as " + name);
//		    }
//		    catch(Exception e) {
//		      System.out.println("Caught exception while registering: " + e);
//		      System.exit(-1);
//		    }
		  }
}
