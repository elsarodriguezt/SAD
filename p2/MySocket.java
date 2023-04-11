/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2;
import java.net.Socket;
import java.io.*;
import java.net.SocketException;

/**
 *
 * @author elsar
 */

public class MySocket{
    private Socket socket;
    private BufferedReader buff; 
    private PrintWriter pw; 

    public MySocket(String hostAddress, int hostPort){
	try{
            this.socket = new Socket(hostAddress, hostPort);
            buff = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);
	}catch(IOException e){
            e.printStackTrace();
	}
    }

    public MySocket(Socket socket){
	try{
            this.socket = socket;
	    buff = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    pw = new PrintWriter(socket.getOutputStream(), true);
	}catch(IOException e){
            e.printStackTrace();
	}
    }

    public void write(String string){
	pw.println(string);
    }
    
    public String read(){
        String string = null;
	try{
            string = buff.readLine();
	}catch(IOException e){
            e.printStackTrace();
	}
            return string;
    }
    
    public void close(){
        try{
            socket.close();
            buff.close();
            pw.close();
	}catch(IOException e){
            e.printStackTrace();
	}
    }	
}