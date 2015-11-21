/**
 * Created by scruf on 11/20/15.
 */
public class CardButton extends javax.swing.JButton{
    /*
    * @param position The position or index of the represented card in the model
    * */
    public int position;
    /*
    * Construct a CardButton object.
    * */
    CardButton(int pos){
        this.position = pos;
    }
    /*
    * Get the position of the card
    * */
    public int getPos(){return this.position;}
}
