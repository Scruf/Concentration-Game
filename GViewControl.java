import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Observable;
import java.util.ArrayList;
import java.util.Queue;
/**
 * Created by scruf on 11/20/15.
 */

public class GViewControl extends javax.swing.JFrame implements  java.util.Observer{
    public ConcentrationModel concentration;
    public static Observable observer;
    public boolean firstCard = false;
    public static JLabel label;
    public static GViewControl gViewControl;
    public static ArrayList<JButton> buttons;
    public static Queue<JButton>  selected = new LinkedList<JButton>();
    public static Queue<JButton> undoQueu = new LinkedList<>();
    public GViewControl(ConcentrationModel  model){
        this.concentration = model;
    }

    public static void main(String []args){
        observer = new Observable();
        gViewControl = new GViewControl(new ConcentrationModel());
        JButton reset,cheat,undo;
        label = new JLabel("Moves: 0 Select the first card.");
        reset = new JButton("Reset");
        cheat = new JButton("Cheat");
        undo = new JButton("Undo");
        buttons = new ArrayList<JButton>();
        //create empty 16 buttons
        //change their size to 100x100
        //set each button name to a corresponding index

        for(int i=0;i<16;i++){
            buttons.add(new JButton(" "));
            buttons.get(i).setPreferredSize(new Dimension(100, 100));
            buttons.get(i).setName(String.valueOf(i));
            buttons.get(i).setBackground(Color.WHITE);
            buttons.get(i).setBorderPainted(false);
            buttons.get(i).setContentAreaFilled(false);
            buttons.get(i).setOpaque(true);
        }
        observer.addObserver(gViewControl);
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setTitle("Egor Kozitski");
        JPanel panel = new JPanel();
        for(JButton but : buttons){
            panel.add(but);
        }
        panel.add(label,BorderLayout.NORTH);
        panel.add(cheat);
        panel.add(undo);
        panel.add(reset);
        frame.add(panel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);




        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println("The size of the list is " + buttons.size());
        //reset Button will reset the screen to default
        reset.addActionListener(e-> {
            for(JButton btn : buttons){
                btn.setText(" ");
                btn.setBackground(Color.WHITE);
            }
        });

        for(JButton btn : buttons){

            btn.addActionListener(e->{
                undoQueu.add(btn);
              gViewControl.concentration.selectCard(Integer.parseInt(btn.getName()));
                System.out.println(gViewControl.concentration.getCards() + "THere are " + gViewControl.concentration.howManyCardsUp());
                btn.setBackground(Color.BLUE);
                CardButton button = new CardButton(Integer.parseInt(btn.getName()));
                gViewControl.update(null,new ActionPerformed(btn,Integer.parseInt(btn.getName())));
               /* btn.setText(String.valueOf(gViewControl.concentration.getCards().get(Integer.parseInt(btn.getName()))));*/

            });
        }
        //undo button
        undo.addActionListener(e -> {
            try {
                //pull from the queue of buttons that has been pressed previously
                JButton btn = undoQueu.poll();
                btn.setText(" ");
                btn.setBackground(Color.WHITE);
            } catch (NullPointerException exc) {
                JOptionPane.showMessageDialog(null, "Nothing to undo");
            }

        });
        //cheat button
        cheat.addActionListener(e->{
            JFrame cheatFrame  = new JFrame();
            ArrayList<JButton> cheatButtons = new ArrayList<JButton>();
            cheatFrame.setSize(375,375);
            cheatFrame.setTitle("Cheat Concentration Game");
            JPanel cheatPanel =  new JPanel();
            for(int i=0;i<16;i++){
                cheatButtons.add(new JButton(String.valueOf(gViewControl.concentration.cheat().get(i))));
                cheatButtons.get(i).setPreferredSize(new Dimension(75,75));
            }
            for(JButton btn : cheatButtons){
                cheatPanel.add(btn);
            }
            cheatFrame.add(cheatPanel);
            cheatFrame.setVisible(true);
            cheatFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);


        });

    }
    public String parseButton(String temp){
        return "-"+temp+"-";
    }

    @Override
    public void update(Observable observable, Object o) {
        ActionPerformed action = (ActionPerformed)o;
        JButton btn = action.getButton();
        System.out.print("Size of the queue is "+selected.size());

        String text = String.valueOf(gViewControl.concentration.getCards().get(action.getPos()));
        btn.setText(text);
        selected.add(btn);

        if(selected.size()==2){
            JButton first;
            JButton second;

                first=selected.poll();
                second=selected.poll();
            String txt = String.valueOf(gViewControl.concentration.getCards().get(action.getPos()));
            second.setText(txt);
            if(first.getText().equals(second.getText())){
                first.setBackground(Color.GREEN);
                second.setBackground(Color.GREEN);
            }
            else{
                first.setBackground(Color.WHITE);
                second.setBackground(Color.WHITE);
                first.setText(" ");
                second.setText(" ");
                selected.remove(first);
                selected.remove(second);
            }


        }


        firstCard=!firstCard;
        if(firstCard){
            label.setText("Moves: "+gViewControl.concentration.getMoveCount()+" Select the first card. ");

        }
        else{
            label.setText("Moves: "+gViewControl.concentration.getMoveCount()+" "+"Select the second card.");
        }

        }

    }


