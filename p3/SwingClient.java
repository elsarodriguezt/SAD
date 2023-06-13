
/**
 *
 * @author elsar
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class SwingClient implements ActionListener {

    private Document doc;
    private SimpleAttributeSet setAtribut;
    private JListSW llista;
    private JTextField missatge;
    private JButton boto;
    private MySocket socket;
    private String nom;

    public SwingClient(String nom, MySocket socket) {
        this.socket = socket;
        this.nom = nom;
    }

// With this method, we write the messages of the user - client, and we pass them to the message box.
    public void actionPerformed(ActionEvent event) {
        String text = missatge.getText();
        missatge.setText("");
        if (text.length() > 0) {
            socket.write(nom + "> " + text);
            try {
                doc.insertString(doc.getLength(), text + "\n", setAtribut);
            } catch (BadLocationException e) {
                System.out.println("Error. Missatge NO valid.");
            }
        }
    }

// With the following method, we take the messages from the server, we add the user who sent it to the 
// JList list if it is a new user, and we print the message in the message box.
    public void addMessage(String text) {
        String rcv[] = text.split("> ", 2);
        if (rcv[1].equals("EXIT")) {
            try {
                doc.insertString(doc.getLength(), rcv[0] + " -> " + "ha abandonat el xat." + "\n", setAtribut);
            } catch (BadLocationException e) {
                System.out.println("Error. Missatge NO valid.");
            }
            llista.removeUser(rcv[0]);

        } else {
            llista.addUser(rcv[0]);
            try {
                doc.insertString(doc.getLength(), rcv[0] + " -> " + rcv[1] + "\n", setAtribut);
            } catch (BadLocationException e) {
                System.out.println("Error. Missatge NO valid.");
            }
        }
    }

// We instantiate the graphical display.
    public void createAndShowGUI(String name) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame(name);

        JPanel out = new JPanel();
        out.setLayout(new BoxLayout(out, BoxLayout.LINE_AXIS));
        JTextPane pane = new JTextPane();
        pane.setEditable(false);
        setAtribut = new SimpleAttributeSet();
        StyleConstants.setBold(setAtribut, true);
        pane.setCharacterAttributes(setAtribut, true);
        setAtribut = new SimpleAttributeSet();
        doc = pane.getStyledDocument();
        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setPreferredSize(new Dimension(350, 150));
        out.add(scrollPane, BorderLayout.CENTER);

        llista = new JListSW();
        JScrollPane jscroll = new JScrollPane(llista.getJList());
        out.add(jscroll);
        JPanel inp = new JPanel();
        inp.setLayout(new BoxLayout(inp, BoxLayout.LINE_AXIS));
        missatge = new JTextField(30);
        boto = new JButton("Enviar");
        missatge.addActionListener(this);
        boto.addActionListener(this);
        inp.add(missatge);
        inp.add(boto);
        frame.add(out, BorderLayout.CENTER);
        frame.add(inp, BorderLayout.PAGE_END);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(450, 250));
        frame.setVisible(true);
    }

}
