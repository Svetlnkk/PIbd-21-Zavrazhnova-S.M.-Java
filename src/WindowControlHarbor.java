import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Objects;

public class WindowControlHarbor extends JPanel{
    private Harbor<ITransport, LightMotor> harbor;
    JButton btnParkingSkiff=new JButton("<html>Припарковать<br>лодку");
    JButton btnParkingBoat=new JButton("<html>Припарковать<br>катер");
    JButton btnTake=new JButton("Забрать");
    JLabel labelTake=new JLabel("Забрать водный транспорт");
    JLabel labelPlace=new JLabel("Место: ");
    JFormattedTextField txtIndexPlace;
    private ITransport boat;
    CanvasBoat canvasBoat=new CanvasBoat();

    private void Draw(Graphics g){
        harbor.Draw(g);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Draw(g);
    }

    public void addButton(JComponent btn, int btnX, int btnY, int btnWidth, int btnHeigth){
        btn.setBounds(btnX, btnY, btnWidth, btnHeigth);
        add(btn);
    }

    public WindowControlHarbor(){
        harbor=new Harbor<ITransport, LightMotor>(1100, 664);
        canvasBoat.setModal(true);
        setBackground(Color.white);
        setLayout(null);
        addButton(labelTake, 1040, 170, 189, 17);
        addButton(labelPlace, 1050, 200, 57, 18);
        addButton(btnParkingSkiff, 1041, 12, 149, 62);
        addButton(btnParkingBoat, 1041, 95, 149, 62);
        addButton(btnTake, 1070, 240, 95, 31);
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setAllowsInvalid(false);
        txtIndexPlace = new JFormattedTextField(formatter);
        addButton(txtIndexPlace, 1110, 200, 42, 22);

        btnParkingSkiff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color mainColor = JColorChooser.showDialog( WindowControlHarbor.this, "Выберите цвет лодки", Color.BLUE );
                if (mainColor != null) {
                     boat=new Skiff(100, 1000, mainColor);
                    if (harbor.addSkiff(boat) >-1) {
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(WindowControlHarbor.this, "Гавань переполнена", "Сообщение", JOptionPane.INFORMATION_MESSAGE );
                    }
                }
            }
        });
        btnParkingBoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color mainColor = JColorChooser.showDialog( WindowControlHarbor.this, "Выберите цвет катера", Color.BLUE );
                if(mainColor!=null){
                    Color dopColor = JColorChooser.showDialog( WindowControlHarbor.this, "Выберите цвет катера", Color.BLUE );
                    if(dopColor!=null){
                        boat = new Boat(100, 1000, mainColor, dopColor,
                                true, true);
                        if ((harbor.addSkiff(boat)>-1))
                        {
                            repaint();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(WindowControlHarbor.this, "Гавань переполнена", "Сообщение", JOptionPane.INFORMATION_MESSAGE );
                        }
                    }
                }
            }
        });
        btnTake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Objects.equals(txtIndexPlace.getText(),""))
                {
                    boat = harbor.remove(Integer.parseInt(txtIndexPlace.getText()));
                    if (boat != null)
                    {
                        canvasBoat.SetSkiff(boat);
                        canvasBoat.setVisible(true);

                    }
                    else
                        JOptionPane.showMessageDialog(WindowControlHarbor.this , "Парковочное место пусто", "Сообщение", JOptionPane.INFORMATION_MESSAGE );
                    repaint();
                }
            }
        });
    }
}
