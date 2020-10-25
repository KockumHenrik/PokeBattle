package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import GUI.CharacterSelection;
import GUI.IpInputBox;

public class Client {
	public static void main(String[] args) throws InterruptedException {
		try {
			IpInputBox iib = new IpInputBox();
			iib.popUpBox();
			while(iib.getIp() == null) {	
				Thread.sleep(10);
			}
			//Connect to server
			Socket s = new Socket(iib.getIp(), 4999);
			InputStreamReader in = new InputStreamReader(s.getInputStream());
			BufferedReader bf = new BufferedReader(in);
			PrintWriter pr = new PrintWriter(s.getOutputStream());
					
			System.out.println(iib.ip + "   Client");
			pr.println(iib.ip);
			pr.flush();
						
			String str = bf.readLine();
			String str2 = bf.readLine();
			String order = bf.readLine();
			System.out.println(String.format("%n%s%n%s", str, str2));
						
			if(order.contains("You are player 1")) {
				String str3 = bf.readLine();
				while(str3 == null) {
					str3 = bf.readLine();
					Thread.sleep(10);
				}
				System.out.println(str3);
			}
			//
			//Character Selection
			CharacterSelection characterSelection = new CharacterSelection();
			characterSelection.characterSelect();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
