/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2;
        
import java.lang.Thread;
import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author elsar
 */
public class Server {
    private static final int PORT = 6666;
    private static Lock lock = new ReentrantLock();
    private static HashMap<String, MySocket> usuaris = new HashMap<>();

    public static void main(String[] args){//por algun motivo el compilador me obliga a declaralo asi?
        MyServerSocket msSocket = new MyServerSocket(PORT);
	System.out.println("Inicialitzant el servidor. Esperant als usuaris.");
        
	while(true){
            
            MySocket client = msSocket.accept();
            new Thread(){
                
		public void run(){
                    client.write("Introdueix el teu usuari per aquest xat: ");
                    String nick = client.read();
                    afegirUsuari(nick, client);
                    String linia;
                    while((linia = client.read()) != null){
                        broadcast(linia, nick);
                        System.out.println(nick + " ha escrit: "+ linia);
                    }
                    eliminarUsuari(nick);
                    client.close();
				}			
            }.start();
        }
    }

    public static void afegirUsuari(String usuari, MySocket socket){
	lock.lock();
	usuaris.put(usuari, socket);
	System.out.println("L'usuari <"+ usuari + "> ha entrat al xat.");
	lock.unlock();
    }

    public static void eliminarUsuari(String usuari){
	lock.lock();
	usuaris.remove(usuari);
	System.out.println("<"+ usuari + "> ha sortit del xat.");
	lock.unlock();
    }
    
    public static void broadcast(String missatge, String nick){
	lock.lock();
	for(Map.Entry<String, MySocket> entry : usuaris.entrySet()){
            MySocket s = entry.getValue();
            if(!nick.equals(entry.getKey())){
		s.write(nick+"> " + missatge);
            }
	}
	lock.unlock();
    }
}
    
