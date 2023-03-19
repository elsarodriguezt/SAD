package p1_MVC;

/**
 *
 * @author Carlota
 */
public class Keys {
/*

    public static final int END2 = 3004;
    public static final int ENTER2 = 3005;
    public static final int ESC2 = 13;
    public static final int SUPR2 = 3007;
    public static final int DEL2 = 127;
    public static final int INSERT2 = 3009;
  */  
    public static final int FWD2 = 3001;
    public static final int BACK2 = 3002;
    public static final int HOME2 = 3003;
    public static final int t_INSERT = 3004;
    public static final int t_SUP = 3005;
    public static final int t_END = 3006;
    public static final int t_INTRO = 3007;
    public static final int t_ESC = 13;
    public static final int t_ERROR = 3008;
    
    public static final int t_DEL = 127;
    

    public static final String s_RIGHT = "\033[C";
    public static final String s_LEFT = "\033[D";
    public static final String s_HOME = "\033";
    public static final String s_END = "\033"; 
    public static final String s_INSERT = "\033[4h";
    public static final String s_SUP = "\033[P";
    public static final String s_DEL = "\033[D\033[P";    
    
    public String make(int name, int pos){
        String tmp;
        if(name==HOME2){
            tmp = "\033[" + pos + "D";
            return tmp;
        }
        else if(name== t_END){
            tmp= "\033[" + pos + "C";
            return tmp;
        }
        else
            return "";
    }
    
    public Keys(){//constructor;
        
    }
    
}
