/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1_noMVC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 *
 * @author elsar
 */
public class EditableBufferedReader extends BufferedReader {
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
            System.out.println("Error al passsar a mode raw");
        }
    }
       
    public void unsetRaw(){
        try{
            String[] com ={"/bin/sh", "-c", "stty echo cooked </dev/tty"};
            Runtime.getRuntime().exec(com).waitFor(); //REV: uan necesario es añadir un waitfor
        }catch(Exception e){
            System.out.println("Error al passsar a mode cooked");
        }
    }
    
    public int read() throws IOException{
        int car = super.read();     
        if(car==Keys.ESC){ 
            super.read();
            car=super.read();              
            switch(car){
                    case Keys.FWD:                        
                        return Keys.FWD2;
                    case Keys.BACK:
                        return Keys.BACK2;
                    case Keys.HOME:
                        return Keys.HOME2;
                    case Keys.END:                        
                        return Keys.END2; 
                    case Keys.SUPR:
                        super.read(); 
                        return Keys.SUPR2;
                    case Keys.INSERT:
                        super.read();
                        return Keys.INSERT2;
            }     
        }
        return car;             
    }
    
    @Override
    public String readLine() throws IOException{
        this.setRaw();
        int car, aux1, aux2;
        
        while((car=this.read())!=Keys.ESC2){
            switch(car){
                case Keys.FWD2:   
                        if(linia.dreta()){
                            System.out.print(Keys.MOU_DRETA);
                        }
                    break;
                case Keys.BACK2: 
                        linia.esquerra();
                        System.out.print(Keys.MOU_ESQ);
                    
                    break;
                case Keys.HOME2:
                    aux1= linia.anarInici();
                    System.out.print(keys.make(Keys.HOME2, aux1));
                    break;

                case Keys.END2:
                    aux2 = linia.anarFinal();
                    if(aux2>0){
                        System.out.print(keys.make(Keys.END2, aux2));                    
                    }                    
                    
                case Keys.INSERT2:
                    linia.insertar();                    
                    break;
                case Keys.DEL2:  
                        linia.esborrar();
                        System.out.print(Keys.ESBORRA);                                                                     
                    break;
                case Keys.SUPR2: 
                        linia.suprimir();                        
                        System.out.print(Keys.SUPRIMIR);                                           
                    break;                
                default:                   
                    if(linia.escriure((char) car)){
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
