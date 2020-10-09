import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import processing.core.PApplet;

public class Main extends PApplet {
	
	private int r;
	private int g;
	private int b;
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader read;
	private BufferedWriter write;
	private String nombre;
	private int pX = 250;
	private int pY = 250;
	
	private TCPSingleton tcp;
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("Main");

	}
	
	public void settings () {
		size(500,500);
		
	}
	
	public void setup () {
		
		//initServer();
		
		tcp = TCPSingleton.getInstance();
		tcp.setObserver(this);
		
	
		
		r = (int)random(0,255);
		g = (int)random(0,255);
		b = (int)random(0,255);
		
		
	}
	
	public void draw () {
		background (255);
		fill (r,g,b);
		ellipse (pX, pY, 50,50);
		if (nombre != null) {
			textAlign (CENTER);
			text(nombre, pX, pY - 40);
		}
	}
	
	
	public void RecibirMsn (String mensaje) {
		nombre = mensaje;
	}
	
	public void movimiento (int posX, int posY) {
		pX = posX;
		pY = posY;
	}
	
	public void colors (int rs, int gs, int bs) {
		r = rs;
		g = gs;
		b = bs;
		System.out.println(""+r);
		System.out.println(""+g);
		System.out.println(""+b);
	}


	
	

}
