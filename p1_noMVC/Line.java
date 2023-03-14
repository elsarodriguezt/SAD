package p1_noMVC;

import java.util.ArrayList;

/**
 *
 * @author elsar
 */
public class Line {

    private int cursor;
    private int longitud;
    private ArrayList<Character> linia; 
    private boolean modeInsert;
    
    public Line() {
        cursor = 0;
        modeInsert = false;
        linia = new ArrayList<Character>();
    }

    public String toString() {
        String string = "";        
        for (int i = 0; i < this.linia.size(); i++) {
            string+= this.linia.get(i);            
        }
        return string;
    }
    
    public boolean dreta(){
        int aux=this.cursor;
        if(this.cursor < this.linia.size()){//si no estem al final de la linea
            this.cursor++; 
        }
        if (aux==this.cursor){
            return false;
        }
        return true;

    }
    
    public void esquerra(){
        if(this.cursor !=0){//si no estem al principi de la linia 
            this.cursor--;
        }
    }
    
    public int anarInici(){
        int aux = this.cursor;
        this.cursor=0;
        return aux;
    }
    
    public int anarFinal(){ 
        int aux = this.cursor;       
        this.cursor = this.linia.size();                
        return (this.cursor-aux);       
    }
    
    public void insertar(){
        this.modeInsert=!this.modeInsert;
    }
    
    public void esborrar(){
        try{
        if(this.cursor >0){
            this.linia.remove(this.cursor-1);
            this.cursor--;                        
        }                
        }catch(Exception e){
            e.printStackTrace();
            System.out.print("\007");            
        }        
    }
    
    public void suprimir(){
        try {
            if (this.cursor < this.linia.size()) {
                this.linia.remove(this.cursor);
                if (this.cursor != 0) {                    
                }                
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("\007");
        }
    }

    public boolean escriure(char car){ 
        if(this.modeInsert){ 
            if(this.cursor < this.linia.size()){//no estem al final de la linia, canviem la lletra
                this.linia.set(this.cursor, car);
            }else{
                this.linia.add(this.cursor,car);
            }
            this.cursor++;
            return true;
            
        }else{
            this.linia.add(this.cursor, car); 
            this.cursor++;            
            return false;
        }
        
    }
       
}