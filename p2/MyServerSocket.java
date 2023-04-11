/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2;
import java.net.ServerSocket;
import java.io.*;


/**
 *
 * @author elsar
 */
public class MyServerSocket {
    private ServerSocket socket;
    
    public MyServerSocket(int port){
        try{
            this.socket = new ServerSocket(port);
	}catch(IOException e){
            e.printStackTrace();
	}	
    }

    public MySocket accept(){
    //Escolta les connexions del host
        try{
            return new MySocket(socket.accept());
	}catch(IOException e){
            e.printStackTrace();
	}
	return null;	
    }
    
    public void close(){
        try{
            this.socket.close();
	}catch(IOException e){
            e.printStackTrace();
	}
    }
}