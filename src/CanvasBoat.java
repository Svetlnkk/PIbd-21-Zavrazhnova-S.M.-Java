import javax.swing.*;

public class CanvasBoat extends JDialog{
    WindowControl windowControl=new WindowControl();
    public void SetSkiff(ITransport skiff){
        windowControl.SetSkiff(skiff);
    }
    public CanvasBoat(){
        setTitle("Окно с изображением");
        setSize(818, 497);
        setLocation(400, 400);
        add(windowControl);

    }
}
