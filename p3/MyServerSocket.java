
/**
 *
 * @author elsar
 */
import java.net.ServerSocket;
import java.io.*;

public class MyServerSocket{

	private ServerSocket socketServidor;

	public MyServerSocket(int serverPort){
		try{
			this.socketServidor = new ServerSocket(serverPort);
		}catch(IOException e){
			e.printStackTrace();
		}
	}


	public MySocket accept(){
	// Listens for connections from host.
		try{
			return new MySocket(socketServidor.accept());
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}

	public void close(){
		try{
			this.socketServidor.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}