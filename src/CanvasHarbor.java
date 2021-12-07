import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CanvasHarbor extends JFrame{
    WindowControlHarbor windowControlHarbor=new WindowControlHarbor();
    JFileChooser fileChooser = new JFileChooser();
    JMenuBar menuBar=new JMenuBar();
    JMenuItem openFile=new JMenuItem("Открыть");
    JMenuItem saveFile=new JMenuItem("Сохранить");
    JMenuItem saveSeparateHarbor=new JMenuItem("Сохранить выбранную парковку");
    JMenuItem loadSeparateHarbor = new JMenuItem( "Загрузить отдельную парковку" );
    JMenu file=new JMenu("Файл");

    public CanvasHarbor(){
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
                    if(windowControlHarbor.saveHarbors(fileChooser.getSelectedFile())) {
                        JOptionPane.showMessageDialog(null, "Сохранили", "Результат", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog( null, "Не удалось сохранить файл", "Результат", JOptionPane.ERROR_MESSAGE );
                    }
                }
            }
        });

        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showDialog( null, "Загрузить из файла") == JFileChooser.APPROVE_OPTION) {
                    if(windowControlHarbor.loadHarbors(fileChooser.getSelectedFile())) {
                        JOptionPane.showMessageDialog(null, "Загрузили", "Результат", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog( null, "Не удалось загрузить файл", "Результат",JOptionPane.ERROR_MESSAGE );
                    }
                }
            }
        });

        saveSeparateHarbor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showDialog( null, "Сохраннить гавань" )== JFileChooser.APPROVE_OPTION) {
                    if(windowControlHarbor.saveSeparateHarbor(fileChooser.getSelectedFile())) {
                        JOptionPane.showMessageDialog(null, "Сохранили гавань " , "Результат", JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog( null, "Не удалось сохранить выбранную гавань","Результат",JOptionPane.ERROR_MESSAGE );
                    }
                }
            }
        });

        loadSeparateHarbor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showDialog(null, "Загрузить из файла") == JFileChooser.APPROVE_OPTION) {
                   if(windowControlHarbor.loadSeparateHarbor(fileChooser.getSelectedFile()))
                    JOptionPane.showMessageDialog( null, "Загрузили гавань", "Результат",JOptionPane.WARNING_MESSAGE );
                }
                else {
                    JOptionPane.showMessageDialog( null, "Не удалось загрузить файл","Результат",JOptionPane.ERROR_MESSAGE );
                }
            }
        });
    }
    public static void main(String[] args){
        CanvasHarbor harbor=new CanvasHarbor();
    }
}
