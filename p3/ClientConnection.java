
/**
 *
 * @author elsar
 */

import java.util.Scanner;


public class ClientConnection{

	public static final String SERVER_HOST = "localhost";
	public static final int SERVER_PORT = 8888;
         
	//public static void main(String hostAddress, int hostPort, String nick){
	public static void main(String[] args){         //el compilador m obliga a declarar aixi
                
		MySocket mySocket = new MySocket(SERVER_HOST, SERVER_PORT);
                Scanner scan= new Scanner(System.in); 
                System.out.print("Introdueix el teu nickname: ");  
                String nickname= scan.nextLine();  
 
		SwingClient swClient = new SwingClient(nickname, mySocket);
		swClient.createAndShowGUI(nickname);

		new Thread(){
			public void run(){
				String missatgeServer;
				while((missatgeServer = mySocket.read()) != null){
					 swClient.addMessage(missatgeServer); 
				}
			}
		}.start();
	}
}
