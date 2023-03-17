/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1_MVC.newpackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
/**
 *
 * @author Carlota
 */
public class EditableBufferedReader extends BufferedReader {
    //InputStreamReader in;
    public boolean error;
    public Line linia;
    public Keys keys;
    public EditableBufferedReader(Reader r){
        super(r);
        this.error=false;
        this.linia=new Line();
        this.keys = new Keys();
    }
    
    public void setRaw(){
        try{
            String[] com ={"/bin/sh", "-c", "stty -echo raw </dev/tty"};
            Runtime.getRuntime().exec(com).waitFor(); //REV: añadir un waitfor?
        }catch(Exception e){
            System.out.println("error setting raw mode");
        }
    }
       
    public void unsetRaw(){
        try{
            String[] com ={"/bin/sh", "-c", "stty echo cooked </dev/tty"};
            Runtime.getRuntime().exec(com).waitFor(); 
        }catch(Exception e){
            System.out.println("error setting cooked mode");
        }
    }

    public int read() throws IOException{
        int tecla, tecla1;
        tecla=super.read();        
        if(tecla!='\033'){             
            return tecla;
        }
        tecla=super.read();

                tecla=super.read();
                switch(tecla){
                    case 'C': return Keys.FWD2;
                    case 'D': return Keys.BACK2;
                    case 'H': return Keys.HOME2;
                    case 'F': return Keys.t_END;
                    case '2': tecla= super.read(); return Keys.t_INSERT;
                    case '3': tecla= super.read(); return Keys.t_SUP; 
                    //control de les tecles: repag, avpag, upArrow, downArrow -> davant no tenir indicacions, anulem les tecles.
                    case 'A': return Keys.t_ERROR;
                    case 'B': return Keys.t_ERROR;
                    case '5': tecla= super.read(); return Keys.t_ERROR;
                    case '6': tecla= super.read();return Keys.t_ERROR;                        
                            
                }
 
        return tecla;
    }
    
    @Override
    public String readLine() throws IOException{
        this.setRaw();

        int car, aux1, aux2;
        
        while((car=this.read())!=Keys.t_ESC){
            switch(car){
                case Keys.FWD2:   
                        if(linia.dreta()){
                            System.out.print(Keys.s_RIGHT);
                        }
                    break;
                case Keys.BACK2: 
                        linia.esquerra();
                        System.out.print(Keys.s_LEFT);                    
                    break;
                case Keys.HOME2:                    
                    aux1= linia.anarInici();
                    System.out.print(keys.make(Keys.HOME2, aux1));
                    break;

                case Keys.t_END:
                    aux2 = linia.anarFinal();
                    if(aux2>0){
                        System.out.print(keys.make(Keys.t_END, aux2));                    
                    }                    
                case Keys.t_INSERT:
                    linia.insertar();                    
                    break;
                case Keys.t_DEL:  
                        linia.esborrar();
                        System.out.print(Keys.s_DEL);                                                                     
                    break;
                case Keys.t_SUP: 
                        linia.suprimir();                        
                        System.out.print(Keys.s_SUP);                                           
                    break;
                case Keys.t_ERROR: 
                    break; //davant la detecció d'aquetes tecles, anulem les tecles
                default:                   
                    if(linia.add((char) car)){
                        System.out.print((char)car);
                    }else{
                        System.out.print("\033[@");
                        System.out.print((char)car);
                    }
                    break;
            }
        
        }
        this.unsetRaw();
        return linia.toString();
        
        
    }
    
}

