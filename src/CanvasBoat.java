import javax.swing.*;

public class CanvasBoat extends JFrame{
    public CanvasBoat(){
        setTitle("Окно с изображение");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(818, 497);
        setLocation(400, 400);
        add(new WindowControl());
        setVisible(true);
    }
    public static void main(String[] args){
        CanvasBoat boat=new CanvasBoat();
    }
}
