import javax.swing.*;
import java.awt.*;
public class PanelShowBoat extends JPanel{
    private ITransport boat;
    public void paintComponent(Graphics g){
        if(boat!=null){
            boat.DrawTransport(g);
        }
    }
    public ITransport getBoat(){
        return boat;
    }
    public void setBoat(ITransport boat){
        this.boat=boat;
    }
}
