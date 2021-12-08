import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class CanvasHarbor extends JFrame{
    WindowControlHarbor windowControlHarbor=new WindowControlHarbor();
    private Logger logger;
    JFileChooser fileChooser = new JFileChooser();
    JMenuBar menuBar=new JMenuBar();
    JMenuItem openFile=new JMenuItem("Открыть");
    JMenuItem saveFile=new JMenuItem("Сохранить");
    JMenuItem saveSeparateHarbor=new JMenuItem("Сохранить выбранную парковку");
    JMenuItem loadSeparateHarbor = new JMenuItem( "Загрузить отдельную парковку" );
    JMenu file=new JMenu("Файл");

    public CanvasHarbor(){
        logger= LogManager.getLogger(CanvasHarbor.class);
        PropertyConfigurator.configure("D:\\Course 2\\ProgTech\\JavaHard\\JavaGit\\src\\log4j.properties");
        setTitle("Гавань");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1220, 700);
        setLocation(200, 100);
        add(windowControlHarbor);
        setVisible(true);
        file.add(openFile);
        file.add(saveFile);
        file.add(saveSeparateHarbor);
        file.add(loadSeparateHarbor);
        menuBar.add(file);
        setJMenuBar(menuBar);

        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showDialog( null, "Сохранить гавани" )== JFileChooser.APPROVE_OPTION) {
                    windowControlHarbor.saveHarbors(fileChooser.getSelectedFile());
                    JOptionPane.showMessageDialog(null, "Сохранили", "Результат", JOptionPane.WARNING_MESSAGE);
                    logger.info( "Сохранено в файл " + fileChooser.getSelectedFile());
                }
                else {
                    logger.warn( "Не удалось сохранить файл" );
                    JOptionPane.showMessageDialog(null, "Не удалось сохранить файл", "Результат", JOptionPane.ERROR_MESSAGE  );
                }
            }
        });

        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showDialog( null, "Загрузить из файла") == JFileChooser.APPROVE_OPTION) {
                    windowControlHarbor.loadHarbors(fileChooser.getSelectedFile());
                    JOptionPane.showMessageDialog(null, "Загрузили", "Результат", JOptionPane.WARNING_MESSAGE);
                    logger.info( "Загружено из файла " + fileChooser.getSelectedFile());
                }
                else{
                    logger.warn( "Не удалось загрузить файл c гаванями" );
                    JOptionPane.showMessageDialog( null, "Не удалось загрузить файл", "Результат",JOptionPane.ERROR_MESSAGE );
                }
            }
        });

        saveSeparateHarbor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showDialog( null, "Сохраннить гавань" )== JFileChooser.APPROVE_OPTION) {
                        windowControlHarbor.saveSeparateHarbor(fileChooser.getSelectedFile());
                        JOptionPane.showMessageDialog(null, "Сохранили гавань " , "Результат", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    logger.warn( "Не удалось сохранить файл" );
                    JOptionPane.showMessageDialog(null, "Не удалось сохранить файл", "Результат",JOptionPane.ERROR_MESSAGE );
                }
            }
        });

        loadSeparateHarbor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showDialog(null, "Загрузить из файла") == JFileChooser.APPROVE_OPTION) {
                    windowControlHarbor.loadSeparateHarbor(fileChooser.getSelectedFile());
                    JOptionPane.showMessageDialog(null, "Загрузили гавань", "Результат", JOptionPane.WARNING_MESSAGE);
                    logger.info( "Гавань загружена из файла " + fileChooser.getSelectedFile());
                }
                else {
                    logger.warn( "Загрузка невозможна" );
                    JOptionPane.showMessageDialog( null, "Не удалось загрузить файл","Результат",JOptionPane.ERROR_MESSAGE );
                }
            }
        });
    }
    public static void main(String[] args){
        CanvasHarbor harbor=new CanvasHarbor();
    }
}
