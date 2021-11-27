import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Objects;

public class WindowControlHarbor extends JPanel{
    private LinkedList<ITransport> linkedListHarborRemoveBoat=new LinkedList<ITransport>();
    private HarborCollection harborCollection;
    private DefaultListModel<String> defListModelHarbors = new DefaultListModel<String>();
    JList<String> jListHarbors;
    JLabel labelHarbors = new JLabel("Гавани");
    JTextField txtHarborName = new JTextField();
    JButton btnAddHarbor = new JButton("Добавить гавань");
    JButton btnRemoveHarbor = new JButton("Удалить гавань");
    JButton btnAddBoat=new JButton("<html>Добавить<br>лодку");
    JButton btnShowBoat=new JButton("Показать");
    JButton btnTake=new JButton("Забрать");
    JLabel labelTake=new JLabel("Забрать водный транспорт");
    JLabel labelPlace=new JLabel("Место: ");
    JFormattedTextField txtIndexPlace;
    private ITransport boat;
    CanvasBoat canvasBoat=new CanvasBoat();
    WindowBoatConfig windowBoatConfig;

    private void Draw(Graphics g){
        if(jListHarbors.getSelectedValue()!=null){
            harborCollection.getHarbor(jListHarbors.getSelectedValue()).Draw(g);
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Draw(g);
    }
    protected void configBoat(){
        windowBoatConfig=new WindowBoatConfig(this);
        windowBoatConfig.setVisible(true);
    }
    public void addBoat(ITransport boat){
        if((harborCollection.getHarbor(jListHarbors.getSelectedValue()).addSkiff(boat))>-1){
            repaint();
        }
        else{
            JOptionPane.showMessageDialog(null, "Гавань переполнена");
        }
    }
    public void addButton(JComponent btn, int btnX, int btnY, int btnWidth, int btnHeigth){
        btn.setBounds(btnX, btnY, btnWidth, btnHeigth);
        add(btn);
    }
    public class listBoxChangeListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            repaint();
        }
    }
    public void ReloadHarbors(){
        int index = jListHarbors.getSelectedIndex();
        defListModelHarbors.clear();
        for (int i = 0; i < harborCollection.keys().size(); i++){
            defListModelHarbors.addElement(harborCollection.keys().get(i));
        }
        if (defListModelHarbors.size() > 0 && (index == -1 || index >= defListModelHarbors.size())){
            jListHarbors.setSelectedIndex(0);
        }
        else{
            if (defListModelHarbors.size() > 0 && index > -1 && index < defListModelHarbors.size()){
                jListHarbors.setSelectedIndex(index);
            }
        }
    }

    public WindowControlHarbor(){
        harborCollection=new HarborCollection(1100, 664);
        canvasBoat.setModal(true);
        setBackground(Color.white);
        setLayout(null);
        addButton(labelHarbors, 1070, 9, 59, 17);
        addButton(txtHarborName, 1018, 35, 172, 22);
        addButton(btnAddHarbor, 1018, 63, 172, 32);
        jListHarbors=new JList<String>(defListModelHarbors);
        jListHarbors.setPrototypeCellValue("Установленный размер");
        jListHarbors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListHarbors.addListSelectionListener(new listBoxChangeListener());
        addButton(new JScrollPane(jListHarbors), 1018, 101, 172, 116);
        addButton(btnRemoveHarbor, 1018, 223, 172, 32);
        addButton(labelTake, 1020, 427, 189, 17);
        addButton(labelPlace, 1030, 457, 57, 18);
        addButton(btnAddBoat, 1021, 272, 149, 62);
        addButton(btnTake, 1050, 507, 95, 31);
        addButton(btnShowBoat, 1050, 560, 95, 31);
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setAllowsInvalid(false);
        txtIndexPlace = new JFormattedTextField(formatter);
        addButton(txtIndexPlace, 1110, 457, 42, 22);

        btnAddBoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configBoat();
            }
        });
        btnTake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Objects.equals(txtIndexPlace.getText(),""))
                {
                    boat = harborCollection.getHarbor(jListHarbors.getSelectedValue()).remove(Integer.parseInt(txtIndexPlace.getText()));
                    if (boat != null)
                    {
                        linkedListHarborRemoveBoat.add(boat);

                    }
                    repaint();
                }
            }
        });
        btnShowBoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(linkedListHarborRemoveBoat.size()>0){
                    canvasBoat.SetSkiff(linkedListHarborRemoveBoat.pop());
                    canvasBoat.setVisible(true);
                }
            }
        });
        btnAddHarbor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtHarborName.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Введите название гавани", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    harborCollection.AddHarbor(txtHarborName.getText());
                    ReloadHarbors();
                }
            }
        });
        btnRemoveHarbor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jListHarbors.getSelectedValue()!=null){
                    if(JOptionPane.showConfirmDialog(null, "Удалить гавань " + jListHarbors.getSelectedValue() + "?") == 0){
                        harborCollection.DelHarbor(jListHarbors.getSelectedValue());
                        ReloadHarbors();
                    }
                }
            }
        });

    }
}
