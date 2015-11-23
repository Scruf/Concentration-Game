import javax.swing.*;

/**
 * Created by Egor Kozitski on 11/22/15.
 */
public class ActionPerformed {
    private JButton button;
    private int pos;
    ActionPerformed(JButton btn, int pos){
        this.button=btn;
        this.pos=pos;
    }
    public JButton getButton(){return this.button;}
    public int getPos(){return this.pos;};
}
