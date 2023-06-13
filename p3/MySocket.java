
/**
 *
 * @author elsar
 */
import java.net.Socket;
import java.io.*;


public class MySocket{

	private Socket socket;
	private BufferedReader buffReader; // Will be used to recieve messages.
	private PrintWriter printWriter; // Will be used to send messages.
        private String nickname;

	public MySocket(String hostAddress, int hostPort){
	// Here we create the socket with the address and port specified.
		try{
			this.socket = new Socket(hostAddress, hostPort);
			buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// We need to set the autoflush to True for the print method.
			printWriter = new PrintWriter(socket.getOutputStream(), true);
                        nickname = hostAddress;
		}catch(IOException e){
			e.printStackTrace();
		}
	}


	public MySocket(Socket soc){
		try{
			this.socket = soc;
			buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			printWriter = new PrintWriter(socket.getOutputStream(), true);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void write(String text){
		printWriter.println(text);
	}

	public String read(){
		String data = null;
		try{
			data = buffReader.readLine();
		}catch(IOException e){
			e.printStackTrace();
		}
		return data;
	}

        public String getName(){   
            return nickname;
        }

	public void close(){
		try{
			socket.close();
			buffReader.close();
			printWriter.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}