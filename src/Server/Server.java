package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		ServerSocket ss;
		try {
			
			//Connect both players
			ss = new ServerSocket(4999);
			Socket s1 = ss.accept();
			InputStreamReader in = new InputStreamReader(s1.getInputStream());
			System.out.println(String.format("%s: connected", s1.getInetAddress().toString()));
			PrintWriter pr = new PrintWriter(s1.getOutputStream());
			pr.println("Connected to server! \nWaiting for other player!\nYou are player 1");
			pr.flush();
			
			Socket s2 = ss.accept();
			InputStreamReader in2 = new InputStreamReader(s2.getInputStream());
			System.out.println(String.format("%s: connected", s2.getInetAddress().toString()));
			
			PrintWriter pr2 = new PrintWriter(s2.getOutputStream());
			pr2.println("Connected to server! \nTwo players are connected!\nYou are player 2");
			pr2.flush();	
			
			PrintWriter pr3 = new PrintWriter(s1.getOutputStream());
			pr3.println("Two players are connected!");
			pr3.flush();
			//
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
