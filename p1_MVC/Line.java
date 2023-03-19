package p1_MVC;

import java.util.ArrayList;
/**
 *
 * @author Carlota
 */
public class Line {
    private int cursor;
    private int longitud;
    private ArrayList<Character> linia; 
    private boolean modeInsert;
    
    public Line() {
        cursor = 0;
        modeInsert = false; //por convenio
        linia = new ArrayList<Character>();
    }

    public String toString() { //REV: make sure
        String res = "";        
        for (int i = 0; i < this.linia.size(); i++) {
            res+= this.linia.get(i);            
        }
        return res;
    }
    
    public boolean dreta(){
        int aux=this.cursor;
        if(this.cursor < this.linia.size()){//si no estoy al final de la línea
            this.cursor++;
            
        }
        if (aux==this.cursor){return false;}
        return true;
    }
    
    public void esquerra(){
        if(this.cursor !=0){        //si no esta al principi
            this.cursor--; 
        } 
    }
    
    public int anarInici(){
        int tmp = this.cursor;
        this.cursor=0;
        return tmp;
    }
    
    public int anarFinal(){ 
        int tmp = this.cursor;       
        this.cursor = this.linia.size();                
        return (this.cursor-tmp);        
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
            System.out.print("Error en esborrar");            
        }      
    }
    
    public void suprimir(){
        try {
            if (this.cursor < this.linia.size()) {
                
                //this.buffer_line.remove(this.cursor+1);
                this.linia.remove(this.cursor);
                if (this.cursor != 0) {                    
                }                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("error en suprimir");
        }
    }

    public boolean escriure(char car){ 
        if(this.modeInsert){ 
            //if(this.cursor < this.buffer_line.size()-1){el -1 hace que si sobreescribo (INSERTAR) "hola" con"adios" el resultado sea "adiosa"
            if(this.cursor < this.linia.size()){//no estoy al final de la linea, tengo que cambiar la letra que hay
                this.linia.set(this.cursor, car);
            }else{
                this.linia.add(this.cursor,car);
            }
            this.cursor++;
            return true;
        }else{
            this.linia.add(this.cursor, car); //los valores se autodesplazan
            this.cursor++;            
            return false;
        }
        
    }
}
