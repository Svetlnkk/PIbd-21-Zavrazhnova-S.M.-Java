import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Objects;

public class WindowControlHarbor extends JPanel{
    private Logger logger;
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
    JButton btnSort = new JButton("Сортировать");
    JFormattedTextField txtIndexPlace;
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
    public void addBoat(Skiff boat){
        try {
            if ((harborCollection.getHarbor(jListHarbors.getSelectedValue()).addSkiff(boat)) > -1) {
                repaint();
                logger.info("Добавлена лодка " + boat);
            }
        }
        catch(HarborOverflowException ex){
            JOptionPane.showMessageDialog(null, "Гавань переполнена");
            logger.warn(ex.getMessage());
        }
        catch (HarborAlreadyHaveException ex){
            logger.warn(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Дублирование", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception ex){
            logger.fatal(ex.toString());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
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
        logger = LogManager.getLogger( WindowControlHarbor.class );
        PropertyConfigurator.configure("D:\\Course 2\\ProgTech\\JavaHard\\JavaGit\\src\\log4j.properties");
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
        addButton(btnSort, 1021, 343, 159, 55);
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
                    try {
                        var boat = harborCollection.getHarbor(jListHarbors.getSelectedValue()).remove(Integer.parseInt(txtIndexPlace.getText()));
                        if (boat != null) {
                            linkedListHarborRemoveBoat.add(boat);
                        }
                        repaint();
                        logger.info("Забрали лодку " + boat + " с места " + jListHarbors.getSelectedValue());
                    }
                    catch(HarborNotFoundException ex){
                        JOptionPane.showMessageDialog(null, "Нет лодки", "Сообщение", JOptionPane.INFORMATION_MESSAGE );
                        logger.warn( ex.getMessage() );
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE );
                        logger.fatal( ex.getMessage() );
                    }
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
                    logger.info( "Добавили гавань " + txtHarborName.getText() );
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
                        logger.info("Удалили гавань " + jListHarbors.getSelectedValue());
                        ReloadHarbors();
                    }
                }
            }
        });
        btnSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jListHarbors.getSelectedValue()!=null){
                    harborCollection.getHarbor(jListHarbors.getSelectedValue()).sort();
                    logger.info("Сортировка уровней");
                    repaint();
                }
            }
        });

    }
    public void saveHarbors(File saveFile){
        try {
            harborCollection.SaveData(saveFile);
        }
        catch(Exception ex){
            logger.fatal(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Неизвестная ошибка при сохранении", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void loadHarbors(File loadFile){
        try {
            harborCollection.LoadData(loadFile);
        }
        catch(HarborOverflowException ex){
            logger.warn( ex.getMessage() );
            JOptionPane.showMessageDialog( null, "Не удалось загрузить файл", "Результат",JOptionPane.ERROR_MESSAGE );
        }
        catch (StackOverflowError ex) {
            logger.error(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Переполнение при загрузке", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception ex) {
            logger.fatal( ex.getMessage() );
            JOptionPane.showMessageDialog( null,ex.getMessage() , "Неизвестная ошибка",JOptionPane.ERROR_MESSAGE );
        }
        finally {
            ReloadHarbors();
            repaint();
        }
    }
    public void saveSeparateHarbor(File saveFile){
        try {
            if (jListHarbors.getSelectedValue() != null) {
                logger.info( "Гавань " + jListHarbors.getSelectedValue() + " сохранена в файл " + saveFile);
                harborCollection.SaveSeparateHarbour(saveFile, jListHarbors.getSelectedValue());
            }
        }
        catch(Exception ex){
            logger.fatal( ex.getMessage());
            JOptionPane.showMessageDialog( null, ex.getMessage(),"Не удалось сохранить выбранную гавань",JOptionPane.ERROR_MESSAGE );
        }
    }
    public void loadSeparateHarbor(File loadFile){
        try {
            harborCollection.LoadSeparateHarbour(loadFile);
        }
        catch(HarborOverflowException ex){
            logger.warn( ex.getMessage() );
            JOptionPane.showMessageDialog( null, "Не удалось загрузить файл", "Результат",JOptionPane.ERROR_MESSAGE );
        }
        catch (StackOverflowError ex) {
            logger.error(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Переполнение при загрузке", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception ex){
            logger.fatal( ex.getMessage() );
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Неизвестная ошибка",JOptionPane.ERROR_MESSAGE  );
        }
        finally {
            ReloadHarbors();
            repaint();
        }

    }
}
