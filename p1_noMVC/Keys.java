package p1_noMVC;

/**
 *
 * @author elsar
 */
public class Keys {
/*
    Aquest codi Java defineix una classe anomenada "Keys" que conté constants 
    que representen els codis de tecla utilitzats en un programa. 
    Aquests codis s'utilitzen per determinar quina acció s'ha de realitzar 
    quan es prem una tecla en el programa.
    */
    
    /*
    Definim les constants per als codis de tecla per a les tecles de fletxa cap 
    endavant, cap enrere, inici, fi i enter, així com la tecla "Supr" (borrar). 
    Aquests codis estan en format ASCII i s'utilitzen en programes que reconeixen 
    codis d'escape.
    */
    public static final int FWD = 67; 
    public static final int BACK = 68;
    public static final int HOME = 72;
    public static final int END = 70; 
    public static final int ENTER = 13; //"CR"
    
    public static final int SUPR = 51; 
    public static final int ESC = 27; 
    public static final int INSERT = 50; 

/*
    Definim constants addicionals pels mateixos codis de tecla, però en un 
    format traduït diferent, que s'utilitza en alguns sistemes o programes 
    que no reconeixen els codis d'escape en format ASCII. En aquest cas, els 
    codis es tradueixen a valors numèrics més grans que 3000.
    */
    public static final int FWD2 = 3001;
    public static final int BACK2 = 3002;
    public static final int HOME2 = 3003;
    public static final int END2 = 3004;
    public static final int ENTER2 = 3005;
    public static final int ESC2 = 13;
    public static final int SUPR2 = 3007;
    public static final int DEL2 = 127;
    public static final int INSERT2 = 3009;

    //codes
        
    public static final String MOU_DRETA = "\033[C"; //moure cursor cap a la dreta
    public static final String MOU_ESQ = "\033[D"; //moure cursor cap a esquerra
    public static final String ANAR_HOME = "\033"; //moure cursor cap al principi de la linia actual
    public static final String ANAR_FINAL = "\033"; //moure cursor cap al final de la linia actual
    public static final String ESCRIU = "\033[4h"; //canviar el mode de la linia per a l'escriptura
    public static final String SUPRIMIR = "\033[P"; //suprimir el caràcter situat sota el cursor
    public static final String ESBORRA = "\033[D\033[P";  //moure el cursor una posició cap a l'esquerra i després suprimir el caràcter situat sota el cursor 
    
    public String make(int name, int pos){
        String aux;
        if(name==HOME2){
            aux = "\033[" + pos + "D"; //mou el cursor a l'esquerra en la mateixa línia
            return aux;
        }
        else if(name== END2){
            aux= "\033[" + pos + "C"; //mou el cursor a la dreta en la mateixa línia
            return aux;
        }
        else
            return "";
    }
     
    public Keys(){//constructor;
        
    }
    
}
