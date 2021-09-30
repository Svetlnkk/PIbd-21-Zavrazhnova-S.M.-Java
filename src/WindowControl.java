import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class WindowControl extends JPanel{
    private ITransport skiff;
    JButton btnCreateSkiff=new JButton("Создать лодку");
    JButton btnCreateBoat=new JButton("Создать катер");
    JButton btnUp = new JButton(new ImageIcon("src/up.png"));
    JButton btnDown = new JButton(new ImageIcon("src/down.png"));
    JButton btnLeft = new JButton(new ImageIcon("src/left.png"));
    JButton btnRight = new JButton(new ImageIcon("src/right.png"));

    private void drawAll(){
        skiff.DrawTransport(getGraphics());
        paintComponents(getGraphics());
    }

    public class Click implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            String actionStr=event.getActionCommand();
            switch(actionStr){
                case "Up":
                    skiff.MoveTransport(Direction.Up);
                    break;
                case "Down":
                    skiff.MoveTransport(Direction.Down);
                    break;
                case "Left":
                    skiff.MoveTransport(Direction.Left);
                    break;
                case "Right":
                    skiff.MoveTransport(Direction.Right);
                    break;
            }
            drawAll();
        }
    }
    public void addButton(JButton btn, int btnX, int btnY, int btnWidth, int btnHeigth){
        btn.setBounds(btnX, btnY, btnWidth, btnHeigth);
        add(btn);
    }
    public WindowControl(){
        setBackground(Color.white);
        setLayout(null);
        btnDown.setActionCommand("Down");
        btnUp.setActionCommand("Up");
        btnLeft.setActionCommand("Left");
        btnRight.setActionCommand("Right");
        addButton(btnCreateSkiff, 8, 8, 144, 43);
        addButton(btnCreateBoat, 160, 8, 144, 43);
        addButton(btnUp, 709, 372, 30, 30);
        addButton(btnDown, 709, 408, 30, 30);
        addButton(btnLeft, 673, 408, 30, 30);
        addButton(btnRight, 745, 408, 30, 30);
        btnCreateSkiff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand=new Random();
                skiff=new Skiff(rand.nextInt(100)+300, rand.nextInt(1000)+2000,Color.cyan);
                skiff.SetPosition(rand.nextInt(50)+100, rand.nextInt(50)+100, getWidth(), getHeight());
                drawAll();
            }
        });
        btnCreateBoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand=new Random();
                skiff=new Boat(rand.nextInt(100)+300, rand.nextInt(1000)+2000, Color.cyan, Color.red, true,
                        true);
                skiff.SetPosition(rand.nextInt(50)+100, rand.nextInt(50)+100, getWidth(), getHeight());
                drawAll();
            }
        });
        btnDown.addActionListener(new Click());
        btnUp.addActionListener(new Click());
        btnLeft.addActionListener(new Click());
        btnRight.addActionListener(new Click());

    }
}
