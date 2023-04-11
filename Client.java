/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2;
import java.lang.Thread;
import java.io.*;

/**
 *
 * @author elsar
 */

public class Client{
    
    public static final String HOST = "localhost";
    public static final int PORT = 6666;

    public static void main(String[] args){
	MySocket socket = new MySocket(HOST, PORT);
		
	// Input Thread
        new Thread(){
            public void run(){
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String linia;
		try{
                    while((linia = in.readLine()) != null){
                        socket.write(linia);
                    }
                    socket.close();
                }catch(IOException e){
                    System.out.println("There was an error in the connection.");
                }
            }
	}.start();

	// Output Thread
	new Thread(){
            public void run(){
		String missatge;
		while((missatge = socket.read()) != null){
                    System.out.println(missatge); 
		}
            }
	}.start();
    }
}

