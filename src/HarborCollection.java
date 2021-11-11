import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class HarborCollection {
    public Map<String, Harbor<ITransport, LightMotor>> harborStages=new HashMap<String, Harbor<ITransport, LightMotor>>();

    public ArrayList<String> keys() {

        return new ArrayList<>(harborStages.keySet());
    }

    private int pictureWidth;
    private int pictureHeight;
    ;public HarborCollection(int pictureWidth, int pictureHeight)
    {
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
    }
    public void AddHarbor(String name)
    {
        if (!harborStages.containsKey(name))
        {
            harborStages.put(name, new Harbor<ITransport, LightMotor>(pictureWidth, pictureHeight));
        }
    }
    public void DelHarbor(String name)
    {
        if (harborStages.containsKey(name))
        {
            harborStages.remove(name);
        }
    }
    public Harbor<ITransport, LightMotor> getHarbor(String ind)
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
}
