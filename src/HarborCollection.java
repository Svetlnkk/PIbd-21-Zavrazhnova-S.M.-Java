import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class HarborCollection {
    public Map<String, Harbor<Vehicle, LightMotor>> harborStages=new HashMap<String, Harbor<Vehicle, LightMotor>>();

    public ArrayList<String> keys() {

        return new ArrayList<>(harborStages.keySet());
    }

    private int pictureWidth;
    private int pictureHeight;
    private char separator = ':';
    ;public HarborCollection(int pictureWidth, int pictureHeight)
    {
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
    }
    public void AddHarbor(String name)
    {
        if (!harborStages.containsKey(name))
        {
            harborStages.put(name, new Harbor<Vehicle, LightMotor>(pictureWidth, pictureHeight));
        }
    }
    public void DelHarbor(String name)
    {
        if (harborStages.containsKey(name))
        {
            harborStages.remove(name);
        }
    }
    public Harbor<Vehicle, LightMotor> getHarbor(String ind)
    {
        if (harborStages.containsKey(ind))
        {
            return harborStages.get(ind);
        }
        return null;
    }
    public ITransport getIndex(String indHarbor, int indexBoat){
        if(harborStages.containsKey(indHarbor) && harborStages.get(indHarbor)!=null){
            return harborStages.get(indHarbor).getBoat(indexBoat);
        }
        return null;
    }
    public boolean SaveData(File fileName) throws Exception{
        if (fileName.exists()){
            fileName.delete();
        }
        try(FileWriter fs = new FileWriter( fileName )) {
            fs.write("HarborCollection" + System.lineSeparator());
            for (int i=0; i<harborStages.size(); ++i) {
                Harbor<Vehicle, LightMotor> harbor=harborStages.get(keys().get(i));
                //Начинаем парковку
                fs.write("Harbor" + separator + keys().get(i) + System.lineSeparator());
                ITransport skiff = null;
                for (int j = 0; (skiff = harbor.getBoat(j)) != null; j++) {
                    if (skiff != null) {
                        //если место не пустое
                        //Записываем тип лодки
                        if (skiff.getClass().getSimpleName().equals("Skiff")) {
                            fs.write("Skiff" + separator);
                        }
                        if (skiff.getClass().getSimpleName().equals("Boat")) {
                            fs.write("Boat" + separator);
                        }
                        //Записываемые параметры
                        fs.write(skiff + System.lineSeparator());
                    }
                }
            }
            return true;
        }
    }
    public boolean LoadData(File fileName) throws Exception{
        if(!fileName.exists()){
            throw new FileNotFoundException();
        }
        try(BufferedReader reader = new BufferedReader(new FileReader( fileName ))){
            String line = reader.readLine();
            if (line.contains( "HarborCollection" )) {
                harborStages.clear();
            }
            else {
                throw new Exception("Неверный формат файла");
            }
            Vehicle skiff = null;
            String key = "";
            while ((line = reader.readLine()) != null) {
                //идем по считанным записям
                if (line.contains("Harbor")) {
                    key = line.split(String.valueOf(separator))[1];
                    harborStages.put(key, new Harbor<Vehicle, LightMotor>(pictureWidth, pictureHeight));
                    continue;
                }
                if(line.isEmpty()){
                    continue;
                }
                if (Objects.equals(line.split(String.valueOf(separator))[0], "Skiff")) {
                    skiff = new Skiff(line.split(String.valueOf(separator))[1]);
                }
                else{
                    if (Objects.equals(line.split(String.valueOf(separator))[0], "Boat")) {
                        skiff = new Boat(line.split(String.valueOf(separator))[1]);
                    }
                }
                var result=harborStages.get(key).addSkiff(skiff);
                if(result==-1){
                    throw new HarborOverflowException();
                }
            }
            return true;

        }
    }

    public boolean SaveSeparateHarbour(File fileName, String key) throws Exception{
        if(!harborStages.containsKey(key)){
            throw new Exception("Такой гавани нет");
        }
        if(fileName.exists()){
            fileName.delete();
        }
        try(FileWriter fw = new FileWriter( fileName )){
            Harbor<Vehicle, LightMotor> level = harborStages.get(key);
            fw.write( "Harbor" + separator + key + System.lineSeparator() );
            ITransport skiff = null;
            for (int i = 0; (skiff = level.getBoat( i )) != null; i++) {
                if(skiff!=null) {
                    if (skiff.getClass().getSimpleName().equals("Skiff")) {
                        fw.write("Skiff" + separator);
                    }
                    if (skiff.getClass().getSimpleName().equals("Boat")) {
                        fw.write("Boat" + separator);
                    }
                    fw.write(skiff + System.lineSeparator());
                }
            }
            return true;
        }
    }

    public boolean LoadSeparateHarbour(File fileName) throws Exception{
        if(!fileName.exists()){
            throw new FileNotFoundException();
        }
        try(BufferedReader reader = new BufferedReader( new FileReader( fileName ) )) {
            String line = reader.readLine();
            if(!line.contains("Harbor" + String.valueOf(separator))){
                throw new Exception("Неверный формат файла");
            }
            else{
                String key=line.split(String.valueOf(separator))[1];
                if(harborStages.containsKey(key)){
                    harborStages.get(key).clear();
                }
                harborStages.put(key, new Harbor<Vehicle, LightMotor>(pictureWidth, pictureHeight));
                Vehicle skiff = null;
                while ((line = reader.readLine()) != null) {
                    if (Objects.equals(line.split(String.valueOf(separator))[0], "Skiff")) {
                        skiff = new Skiff(line.split(String.valueOf(separator))[1]);
                    }
                    else{
                        if (Objects.equals(line.split(String.valueOf(separator))[0], "Boat")) {
                            skiff = new Boat(line.split(String.valueOf(separator))[1]);
                        }
                    }
                    var result = harborStages.get(key).addSkiff(skiff);
                    if (result == -1) {
                        throw new StackOverflowError("Не получилось загрузить лодку");
                    }
                }
            }
            return true;
        }
    }
}
