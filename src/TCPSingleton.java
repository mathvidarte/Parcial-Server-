


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

import model.Color;
import model.Generic;
import model.Move;
import model.Posicion;
import processing.core.PApplet;

public class TCPSingleton extends Thread{
	

	private static TCPSingleton unicainstancia;
	private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader read;
    private BufferedWriter write;
    private Posicion pos;
    private Move move;
    private Color color;
    private Main main;
    private int posX = 250;
    private int posY = 250;
    private int rs;
    private int gs;
    private int bs;
   
    
    
    public void setObserver (Main main) {
    	this.main = main;
    }

    private TCPSingleton() {

    }

    public static TCPSingleton getInstance() {
        if (unicainstancia == null) {
            unicainstancia = new TCPSingleton();
            unicainstancia.start();
        }
        return unicainstancia;
    }

    public void run () {
        
    	try {
			
			/*InetAddress inetAddress = InetAddress.getLocalHost();
            String ipLocal = inetAddress.getHostAddress();
            System.out.println(""+ipLocal);*/
            
			serverSocket = new ServerSocket (5001);
			System.out.println("esperando conexión");
			socket = serverSocket.accept();
			System.out.println("Conectó");
			
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader (is);
			read = new BufferedReader (isr);
			
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter (os);
			write = new BufferedWriter (osw);
			
			while (true) {
				String line = read.readLine();
				Gson gson = new Gson ();
				Generic generic = gson.fromJson(line, Generic.class);
				System.out.println(""+line);
				
				
				switch (generic.getType()) {
				case "posicion":
					
					pos = gson.fromJson(line, Posicion.class);
					
					pos.getMsn();
					
					main.RecibirMsn(pos.getMsn());
					
					
					
					System.out.println(pos.getMsn());
					break;
					
				case "move":
					
					move = gson.fromJson(line, Move.class);
					
					switch (move.getMove()) {
					case "up":
						main.movimiento(posX, posY -= 4);
						break;
					case "down":
						main.movimiento(posX, posY += 4);
						break;
					case "left":
						main.movimiento(posX -= 4, posY);
						break;
					case "right":
						main.movimiento(posX += 4, posY);
						break;
					}
					break;
					
				case "color":
					
					color = gson.fromJson(line, Color.class);
					rs = (int) (Math.random()*255);
					gs = (int) (Math.random()*255);
					bs = (int) (Math.random()*255);
					
					main.colors(rs , gs , bs);
					System.out.println(""+rs);
					System.out.println(""+gs);
					System.out.println(""+bs);
					break;
					
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Posicion getPos() {
		return pos;
	}

	public void setPos(Posicion pos) {
		this.pos = pos;
	}
    

    
}
