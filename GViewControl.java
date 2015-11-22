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
    public GViewControl(ConcentrationModel  model){
        this.concentration = model;
    }

    public static void main(String []args){

        GViewControl gViewControl = new GViewControl(new ConcentrationModel());
        JButton reset,cheat,undo;
        reset = new JButton("Reset");
        cheat = new JButton("Cheat");
        undo = new JButton("Undo");
        ArrayList<JButton> buttons = new ArrayList<JButton>();

        for(int i=0;i<16;i++){
            buttons.add(new JButton(" "));
        }



        for(JButton btn  : buttons){
            btn.setPreferredSize(new Dimension(100, 100));

        }



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





        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }


    @Override
    public void update(Observable observable, Object o) {
    }
}
