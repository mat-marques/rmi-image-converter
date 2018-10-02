import java.rmi.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.util.*;


public class ServerImp {
	
	/*
	 * Função para converter uma imagem para tons de cinza
	 */
	@Override
	public int[] grayscaleConvertion(int[] image){
		int[] source = new int[image.length];

		System.out.println("Inicio da conversão da imagem recebida.");
		
		
		for(int y = 0; y < image.length; y++){

			int p = image[y];

			int a = (p>>24)&0xff;
			int r = (p>>16)&0xff;
			int g = (p>>8)&0xff;
			int b = p&0xff;

			//calculate average
			int avg = (r+g+b)/3;

			//replace RGB value with avg
			p = (a<<24) | (avg<<16) | (avg<<8) | avg;

			source[y] = p;
		    
		}
		
		System.out.println("Fim da conversão da imagem.");
		return source;
	}
	
	
	/*
	 * Função para registrar um endereço para o servidor
	 */
	@SuppressWarnings("deprecation")
	public static void main(String [] args) {
		    // install RMI security manager
		    System.setSecurityManager(new RMISecurityManager());
		    // arg. 0 = rmi url
		    if (args.length!=1) {
		      System.err.println("Usage: TicketServerImpl <server-rmi-url>");
		      System.exit(-1);
		    }
		    try {  
		      // name with which we can find it = user name
		      String name = args[0];
		      
		      //create new instance
		      ServerImp server = new ServerImp();
		      
		      // register with nameserver
		      Naming.rebind(name, server);
		      System.out.println("Started Server, registered as " + name);
		    }
		    catch(Exception e) {
		      System.out.println("Caught exception while registering: " + e);
		      System.exit(-1);
		    }
		  }
}
