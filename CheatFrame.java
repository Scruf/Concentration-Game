/**
 * Created by scruf on 11/20/15.
 */
import java.util.ArrayList;
public class CheatFrame extends javax.swing.JFrame{
   //@param An ArrayList of CardButtons that are all showing their numbers
    public ArrayList<CardButton> cardButtons;
    //@param The size (of one side) of the board (measured in cards).
    public int size;
    /*
    * Construct a CheatFrame object
    * */
        public CheatFrame(java.util.ArrayList cardButtons, int size){
            cardButtons =  new ArrayList<CardButton>();
            this.cardButtons= cardButtons;
            this.size = size;

        }
}
