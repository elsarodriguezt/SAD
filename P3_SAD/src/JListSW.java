
/**
 *
 * @author elsar
 */

import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class JListSW{
    private JList<String> llista;
    private DefaultListModel<String> llistaModel;

    public JListSW(){
        llistaModel = new DefaultListModel<>();
        llista = new JList<>(llistaModel);
    }

    public JList<String> getJList(){
        return llista;
    }

    public void addUser(String user){
        if (!llistaModel.contains(user)){
            llistaModel.addElement(user);
            sortModel();
        }
    }

    public void removeUser(String user){
        for (int i = 0; i < llistaModel.size(); i++){
            if (llistaModel.get(i).contains(user)){
                llistaModel.remove(i);
                break;
            }
        }
    }

    private void sortModel(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < llistaModel.size(); i++){
            list.add((String) llistaModel.get(i));
        }
        // We need to use both objects in order to sort them
        Collections.sort(list);
        llistaModel.removeAllElements();
        for (String s : list){
            llistaModel.addElement(s);
        }
    }
}