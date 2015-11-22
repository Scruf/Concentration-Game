import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Observable;
import java.util.ArrayList;
/**
 * Created by scruf on 11/20/15.
 */

public class GViewControl extends javax.swing.JFrame implements  java.util.Observer{
    public ConcentrationModel concentration;
    public static Observable observer;
    public GViewControl(ConcentrationModel  model){
        this.concentration = model;
    }

    public static void main(String []args){
        observer = new Observable();
        GViewControl gViewControl = new GViewControl(new ConcentrationModel());
        JButton reset,cheat,undo;
        reset = new JButton("Reset");
        cheat = new JButton("Cheat");
        undo = new JButton("Undo");
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        //create empty 16 buttons
        //change their size to 100x100
        //set each button name to a corresponding index
        for(int i=0;i<16;i++){
            buttons.add(new JButton(" "));
            buttons.get(i).setPreferredSize(new Dimension(100,100));
            buttons.get(i).setName(String.valueOf(i));
        }
        observer.addObserver(gViewControl);
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setTitle("Egor Kozitski");
        JPanel panel = new JPanel();
        for(JButton but : buttons){
            panel.add(but);
        }

        panel.add(cheat);
        panel.add(undo);
        panel.add(reset);
        frame.add(panel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);




        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println("The size of the list is "+buttons.size());

        for(JButton btn : buttons){

            btn.addActionListener(e->{
              gViewControl.concentration.selectCard(Integer.parseInt(btn.getName()));
                System.out.println(gViewControl.concentration.getCards());
               /* btn.setText(String.valueOf(gViewControl.concentration.getCards().get(Integer.parseInt(btn.getName()))));*/
               observer.notify();
            });
        }

    }


    @Override
    public void update(Observable observable, Object o) {
        System.out.println("Something has been changed");
    }
}
