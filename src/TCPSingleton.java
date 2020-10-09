


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
    private Main main;
    private int posX = 250;
    private int posY = 250;
    private int rs = (int) Math.random()*255;
    private int gs = (int) Math.random()*255;
    private int bs = (int) Math.random()*255;
   
    
    
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
			System.out.println("esperando conexi�n");
			socket = serverSocket.accept();
			System.out.println("Conect�");
			
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
					
					System.out.println("arepas");
					
					
					pos = gson.fromJson(line, Posicion.class);
					
					pos.getMsn();
					
					main.RecibirMsn(pos.getMsn());
					
					
					
					System.out.println(pos.getMsn());
					break;
					
				case "move":
					
					move = gson.fromJson(line, Move.class);
					
					switch (move.getMove()) {
					case "up":
						main.movimiento(posX, posY -= 2);
						break;
					case "down":
						main.movimiento(posX, posY += 2);
						break;
					case "left":
						main.movimiento(posX -= 2, posY);
						break;
					case "right":
						main.movimiento(posX += 2, posY);
						break;
					}
					break;
					
				case "color":
					main.colors(rs, gs, bs);
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
    

  /*  public void sendMessage (String msn){
        new Thread (
                () -> {
                    try {
                        write.write(msn+"\n");
                        write.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
        ).start();
    }*/

    
}
