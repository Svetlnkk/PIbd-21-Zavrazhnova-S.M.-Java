import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class WindowBoatConfig extends JFrame{
    JPanel panel=new JPanel();
    private Skiff boat;
    PanelShowBoat panelShowBoat=new PanelShowBoat();
    JLabel labelCharacteristics = new JLabel("");
    JPanel panelBoatType=new JPanel(new FlowLayout( FlowLayout.CENTER, 10, 10 ));
    JPanel panelParameters=new JPanel(new FlowLayout( FlowLayout.CENTER, 10, 10 ));
    JPanel panelColors=new JPanel(new GridLayout( 5, 2, 0, 2 ));
    JLabel labelSkiff = new JLabel("Лодка");
    JLabel labelBoat = new JLabel("Катер");
    JLabel mainColor = new JLabel("Основной цвет");
    JLabel dopColor = new JLabel("Доп. цвет");
    JLabel labeltypesMotors = new JLabel("Виды моторов");
    JLabel labelLightMot = new JLabel("Простой");
    JLabel labelMediumMot = new JLabel("Средний");
    JLabel labelPremiumMot = new JLabel("Дорогой");
    JCheckBox checkBoxDeck = new JCheckBox("Палуба");
    JCheckBox checkBoxMotor = new JCheckBox("Мотор");
    JLabel labelMaxSpeed=new JLabel("Макс. скорость");
    JSpinner spinnerMaxSpeed = new JSpinner(new SpinnerNumberModel(100, 100, 1000, 1));
    JLabel labelWeight=new JLabel("Вес лодки");
    JSpinner spinnerWeight = new JSpinner(new SpinnerNumberModel(100, 100, 1000, 1));
    JButton btnAddBoat = new JButton("Добавить");
    JButton btnCloseWindow = new JButton("Отмена");

    public void componentAdd(JComponent jComponent, int x, int y, int width, int height) {
        jComponent.setBounds(x, y, width, height);
        panel.add(jComponent);
    }
    WindowBoatConfig(WindowControlHarbor wControlHarbor){
        setTitle("Выбор лодки");
        setSize(875, 490);
        setLocation(500, 200);
        add(panel);
        panel.setBackground(Color.white);
        panel.setLayout(null);

        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0));
        labelSkiff.setBorder(border);
        labelBoat.setBorder(border);
        panelShowBoat.setBorder(border);
        mainColor.setBorder(border);
        dopColor.setBorder(border);
        labeltypesMotors.setBorder(border);
        labelLightMot.setBorder(border);
        labelMediumMot.setBorder(border);
        labelPremiumMot.setBorder(border);

        componentAdd(labelSkiff, 45, 55, 100, 35);
        componentAdd(labelBoat, 45, 115, 100, 33);
        panelBoatType.setBorder(BorderFactory.createTitledBorder("Тип лодки"));
        panelBoatType.setBackground(Color.white);
        componentAdd(panelBoatType, 12, 33, 200,148);
        componentAdd(panelShowBoat, 236, 12, 265, 204);
        componentAdd(labelCharacteristics, 236, 12, 265, 204);
        componentAdd(mainColor, 528, 34, 145, 30);
        componentAdd(dopColor, 698, 34, 121, 30);
        componentAdd(checkBoxDeck, 302, 261, 92, 24);
        checkBoxDeck.setBackground(Color.white);
        componentAdd(checkBoxMotor, 302, 313, 99, 24);
        checkBoxMotor.setBackground(Color.white);
        componentAdd(labelMaxSpeed, 12, 265, 142, 20);
        componentAdd(spinnerMaxSpeed, 83, 302, 83, 27);
        componentAdd(labelWeight,17, 346, 101, 20 );
        componentAdd(spinnerWeight, 83, 381, 83, 27);
        panelParameters.setBorder(BorderFactory.createTitledBorder( "Параметры" ));
        panelParameters.setBackground(Color.white);
        componentAdd(panelParameters, 12, 222, 400, 198);
        componentAdd(labeltypesMotors, 490, 232, 100, 20);
        componentAdd(labelLightMot, 490, 272, 100, 30);
        componentAdd(labelMediumMot, 490, 312, 100, 30);
        componentAdd(labelPremiumMot, 490, 352, 100, 30);
        componentAdd(btnAddBoat, 726, 261, 93, 40);
        componentAdd(btnCloseWindow, 726, 346, 93, 40);

        JPanel panelMainCyan=new JPanel();
        panelMainCyan.setBackground(Color.cyan);
        panelMainCyan.setBorder(border);
        componentAdd(panelMainCyan, 528, 87, 50, 50);
        JPanel panelMainPink=new JPanel();
        panelMainPink.setBackground(Color.magenta);
        panelMainPink.setBorder(border);
        componentAdd(panelMainPink, 600, 87, 50, 50);
        JPanel panelMainGreen=new JPanel();
        panelMainGreen.setBackground(Color.green);
        panelMainGreen.setBorder(border);
        componentAdd(panelMainGreen, 528, 146, 50, 50);
        JPanel panelMainOrange=new JPanel();
        panelMainOrange.setBackground(Color.orange);
        panelMainOrange.setBorder(border);
        componentAdd(panelMainOrange, 600, 146, 50, 50);

        JPanel panelDopBlue=new JPanel();
        panelDopBlue.setBackground(Color.blue);
        panelDopBlue.setBorder(border);
        componentAdd(panelDopBlue, 698, 87, 50, 50);
        JPanel panelDopGray=new JPanel();
        panelDopGray.setBackground(Color.gray);
        panelDopGray.setBorder(border);
        componentAdd(panelDopGray, 769, 87, 50, 50);
        JPanel panelDopBlack=new JPanel();
        panelDopBlack.setBackground(Color.black);
        panelDopBlack.setBorder(border);
        componentAdd(panelDopBlack, 698, 146, 50, 50);
        JPanel panelDopRed=new JPanel();
        panelDopRed.setBackground(Color.red);
        panelDopRed.setBorder(border);
        componentAdd(panelDopRed, 769, 146, 50, 50);
        panelColors.setBorder(BorderFactory.createTitledBorder( "Цвета" ));
        panelColors.setBackground(Color.white);
        componentAdd(panelColors, 522, 12, 323, 200);

        var typeDragMouseAdapter = new DragMouseAdapter();
        var colorDragMouseAdapter = new DragMouseAdapter();
        var motorDragMouseAdapter = new DragMouseAdapter();
        labelSkiff.addMouseListener(typeDragMouseAdapter);
        labelSkiff.setTransferHandler(new TransferHandler("text"));
        labelBoat.addMouseListener(typeDragMouseAdapter);
        labelBoat.setTransferHandler(new TransferHandler("text"));
        mainColor.addMouseListener(colorDragMouseAdapter);
        mainColor.setTransferHandler(new TransferHandler("background"));
        dopColor.addMouseListener(colorDragMouseAdapter);
        dopColor.setTransferHandler(new TransferHandler("background"));
        labelLightMot.addMouseListener(motorDragMouseAdapter);
        labelLightMot.setTransferHandler(new TransferHandler("text"));
        labelLightMot.setDropTarget(null);
        labelMediumMot.addMouseListener(motorDragMouseAdapter);
        labelMediumMot.setTransferHandler(new TransferHandler("text"));
        labelMediumMot.setDropTarget(null);
        labelPremiumMot.addMouseListener(motorDragMouseAdapter);
        labelPremiumMot.setTransferHandler(new TransferHandler("text"));
        labelPremiumMot.setDropTarget(null);
        labeltypesMotors.addMouseListener(motorDragMouseAdapter);
        labeltypesMotors.setTransferHandler(new TransferHandler("text"));
        panelMainCyan.addMouseListener(colorDragMouseAdapter);
        panelMainCyan.setTransferHandler(new TransferHandler("background"));
        panelMainCyan.setDropTarget(null);
        panelMainPink.addMouseListener(colorDragMouseAdapter);
        panelMainPink.setTransferHandler(new TransferHandler("background"));
        panelMainPink.setDropTarget(null);
        panelMainGreen.addMouseListener(colorDragMouseAdapter);
        panelMainGreen.setTransferHandler(new TransferHandler("background"));
        panelMainGreen.setDropTarget(null);
        panelMainOrange.addMouseListener(colorDragMouseAdapter);
        panelMainOrange.setTransferHandler(new TransferHandler("background"));
        panelMainOrange.setDropTarget(null);
        panelDopBlue.addMouseListener(colorDragMouseAdapter);
        panelDopBlue.setTransferHandler(new TransferHandler("background"));
        panelDopBlue.setDropTarget(null);
        panelDopBlack.addMouseListener(colorDragMouseAdapter);
        panelDopBlack.setTransferHandler(new TransferHandler("background"));
        panelDopBlack.setDropTarget(null);
        panelDopGray.addMouseListener(colorDragMouseAdapter);
        panelDopGray.setTransferHandler(new TransferHandler("background"));
        panelDopGray.setDropTarget(null);
        panelDopRed.addMouseListener(colorDragMouseAdapter);
        panelDopRed.setTransferHandler(new TransferHandler("background"));
        panelDopRed.setDropTarget(null);
        labelCharacteristics.addMouseListener(typeDragMouseAdapter);
        labelCharacteristics.setTransferHandler(new TransferHandler("text"));

        PropertyChangeListener typeListener=propertyChangeEvent ->{
            if(Objects.equals(labelCharacteristics.getText(), "Лодка")){
                setSkiff();
            }
            if(Objects.equals(labelCharacteristics.getText(), "Катер")){
                setBoat();
            }
            labelCharacteristics.setText("");
        };
        PropertyChangeListener mainColorListener=propertyChangeEvent ->{
            if(boat!=null){
                boat.setMainColor(mainColor.getBackground());
                panelShowBoat.setBoat(boat);
                repaint();
            }
        };
        PropertyChangeListener dopColorListener=propertyChangeEvent ->{
            if(boat!=null && boat.getClass().getSimpleName().equals("Boat")){
                Boat tmpBoat=(Boat)boat;
                tmpBoat.setDopColor(dopColor.getBackground());
                boat=tmpBoat;
                panelShowBoat.setBoat(boat);
                repaint();
            }
        };
        PropertyChangeListener typesMotorsListener=propertyChangeEvent ->{
            if(boat!=null && boat.getClass().getSimpleName().equals("Boat")){
                Boat tmpBoat=(Boat)boat;
                if(Objects.equals(labeltypesMotors.getText(), "Простой")){
                    tmpBoat.setMotors(new LightMotor());
                }
                if(Objects.equals(labeltypesMotors.getText(), "Средний")){
                    tmpBoat.setMotors(new MediumMotors());
                }
                if(Objects.equals(labeltypesMotors.getText(), "Дорогой")){
                    tmpBoat.setMotors(new PremiumMotor());
                }
                boat=tmpBoat;
                panelShowBoat.setBoat(boat);
                repaint();
            }
        };
        labelCharacteristics.addPropertyChangeListener("text", typeListener);
        mainColor.addPropertyChangeListener("background", mainColorListener);
        dopColor.addPropertyChangeListener("background", dopColorListener);
        labeltypesMotors.addPropertyChangeListener("text", typesMotorsListener);
        btnCloseWindow.addActionListener(actionEvent -> dispose());
        btnAddBoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(boat!=null){
                    wControlHarbor.addBoat(boat);
                }
                dispose();
            }
        });
    }
    public void setSkiff(){
        boat = new Skiff((Integer)spinnerMaxSpeed.getValue(), (Integer)spinnerWeight.getValue(), mainColor.getBackground());
        panelShowBoat.setBoat(boat);
        panelShowBoat.getBoat().SetPosition(10, 10, 200, 200);
        repaint();
    }
    public void setBoat(){
        boat = new Boat((Integer)spinnerMaxSpeed.getValue(), (Integer)spinnerWeight.getValue(),
                mainColor.getBackground(), dopColor.getBackground(), checkBoxDeck.isSelected(),checkBoxMotor.isSelected());
        panelShowBoat.setBoat(boat);
        panelShowBoat.getBoat().SetPosition(10, 10, 200, 200);
        repaint();
    }
}
