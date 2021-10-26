import javax.swing.*;

public class CanvasHarbor extends JFrame{
    WindowControlHarbor windowControlHarbor=new WindowControlHarbor();
    public CanvasHarbor(){
        setTitle("Гавань");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1220, 670);
        setLocation(300, 300);
        add(windowControlHarbor);
        setVisible(true);
    }
    public static void main(String[] args){
        CanvasHarbor harbor=new CanvasHarbor();
    }
}
