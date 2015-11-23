import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.util.*;

/**
 * Created by Egor Kozitski on 11/20/15.
 */

public class GViewControl extends javax.swing.JFrame implements  java.util.Observer{
    public ConcentrationModel concentration;
    public static Observable observer;
    public boolean firstCard = false;
    public static JLabel label;
    public static GViewControl gViewControl;
    //@param buttons will hold array list of buttons
    public static ArrayList<JButton> buttons;
    //@param selected will hold all buttons that has been pressed
    public static Queue<JButton>  selected = new LinkedList<JButton>();
    //@param undoQueue will holl all buttons that has been pressed and will remove a button at a time when
    //undo pressed
    public static Stack<JButton> undoQueu = new Stack<>();
    //@param colors will hold arraylist of colors
    public static ArrayList<Color> colors = new ArrayList<>();
    //@param mapOfKeys will hold a number and its matching color
    public static HashMap<String,Color> mapOfKeys = new HashMap<>();
    //@param wasmatched will hold all buttons that has been matched
    public static HashMap<String,Boolean> wasMatched = new HashMap<>();
    public GViewControl(ConcentrationModel  model){
        this.concentration = model;
    }

    public static void main(String []args){
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.ORANGE);
        colors.add(Color.YELLOW);
        colors.add(Color.CYAN);
        colors.add(Color.RED);
        colors.add(Color.GRAY);
        Collections.shuffle(colors);
        Set<String> distinctNumber = new HashSet<>();

        gViewControl = new GViewControl(new ConcentrationModel());
        for(int i=0;i<gViewControl.concentration.cheat().size();i++){
            distinctNumber.add(String.valueOf(gViewControl.concentration.cheat().get(i)));
        }
        Iterator iter = distinctNumber.iterator();
        int counter=0;
        while(iter.hasNext()){
            String value = iter.next().toString();
            if(counter!=7) {
                mapOfKeys.put(value, colors.get(counter));
                wasMatched.put(value,false);
            }
            counter++;
        }
        for(Map.Entry<String,Color> item : mapOfKeys.entrySet()){

        }
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

        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setTitle("Egor Kozitski");
        JPanel panel = new JPanel();
        for(JButton but : buttons){
            panel.add(but);
        }
        panel.add(label,BorderLayout.NORTH);
        //add cheat button to a panel
        panel.add(cheat);
        //add undo button to a panel
        panel.add(undo);
        //add rest button to a panel
        panel.add(reset);
        //add panel to a list
        frame.add(panel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);




        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

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

                btn.setBackground(mapOfKeys.get(btn.getText()));
               gViewControl.update(null,new ActionPerformed(btn,Integer.parseInt(btn.getName())));
               /* btn.setText(String.valueOf(gViewControl.concentration.getCards().get(Integer.parseInt(btn.getName()))));*/

            });
        }
        //undo button
        undo.addActionListener(e -> {
            try {
                //pull from the queue of buttons that has been pressed previously
                JButton btn = undoQueu.pop();
                if(wasMatched.get(btn.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Cannot turn a cards that is matched");
                }
                else{
                btn.setText(" ");}
                btn.setBackground(Color.WHITE);
            } catch (EmptyStackException exc) {
                JOptionPane.showMessageDialog(null, "Nothing to undo");
            }

        });
        //cheat button
        cheat.addActionListener(e -> {
            JFrame cheatFrame = new JFrame();
            ArrayList<JButton> cheatButtons = new ArrayList<JButton>();
            cheatFrame.setSize(375, 375);
            cheatFrame.setTitle("Cheat Concentration Game");
            JPanel cheatPanel = new JPanel();
            for (int i = 0; i < 16; i++) {
                cheatButtons.add(new JButton(String.valueOf(gViewControl.concentration.cheat().get(i))));
                cheatButtons.get(i).setPreferredSize(new Dimension(75, 75));
                cheatButtons.get(i).setBorderPainted(false);
                cheatButtons.get(i).setContentAreaFilled(false);
                cheatButtons.get(i).setOpaque(true);
                cheatButtons.get(i).setBackground(mapOfKeys.get(cheatButtons.get(i).getText()));


            }
            for (JButton btn : cheatButtons) {
                cheatPanel.add(btn);
            }
            cheatFrame.add(cheatPanel);
            cheatFrame.setVisible(true);
            cheatFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);


        });

    }


    @Override
    public void update(Observable observable, Object o) {
        ActionPerformed action = (ActionPerformed)o;
        JButton btn = action.getButton();


        String text = String.valueOf(gViewControl.concentration.getCards().get(action.getPos()));
        btn.setText(text);
        selected.add(btn);

        if(selected.size()==3){
            JButton first;
            JButton second;

                first=selected.poll();
                second=selected.poll();

            if(first.getText().equals(second.getText())){
                first.setBackground(mapOfKeys.get(first.getText()));
                second.setBackground(mapOfKeys.get(second.getText()));
                wasMatched.put(first.getText(),true);
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


