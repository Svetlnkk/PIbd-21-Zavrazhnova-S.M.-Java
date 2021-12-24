import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Harbor<T extends ITransport, U extends ISpeed> implements Iterable<Vehicle>, Iterator<Vehicle> {
    private ArrayList<T> places;
    private int maxCount;
    private int PicWidth;
    private int PicHeight;
    private int placeSizeWidth=200;
    private int placeSizeHeight = 90;
    private int width;
    private int height;

    public Harbor(int picWidth, int picHeight)
    {
        width = picWidth / placeSizeWidth;
        height = picHeight / placeSizeHeight;
        maxCount=width * height;
        places =new ArrayList<T>();
        PicWidth = picWidth;
        PicHeight = picHeight;
    }
    public T getBoat(int index)throws HarborNotFoundException{
        if(index>-1 && index<places.size()){
            return places.get(index);
        }
        return null;
    }

    public int addSkiff(T skiff)throws HarborOverflowException, HarborAlreadyHaveException{
        if(places.size()>=maxCount){
            throw new HarborOverflowException();
        }
        if(places.contains(skiff)){
            throw new HarborAlreadyHaveException();
        }
        places.add(skiff);
        return places.size()-1;
    }

    public T remove(int index)throws HarborNotFoundException{
        if(index>-1 && index<places.size()){
            T removeSkiff=places.get(index);
            places.remove(index);
            return removeSkiff;
        }
        else { throw new HarborNotFoundException( index ); }
    }

    public boolean equal(int numBoat) { // ==
        int numCorrectBoat = 0;
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i) != null) {
                numCorrectBoat++;
            }
        }
        return numBoat == numCorrectBoat;
    }

    public boolean nonEqual(int numCar) { // !=
        return !equal( numCar );
    }

    public void Draw(Graphics g)
    {
        g.setColor(Color.BLACK);
        DrawMarking(g);
        for(int i=0; i<places.size(); i++)
        {
            places.get(i).SetPosition(5 + i % 5 * placeSizeWidth + 5, i /5 * placeSizeHeight + 5, PicWidth, PicHeight);
            places.get(i).DrawTransport(g);
        }
    }
    private void DrawMarking(Graphics g)
    {
        g.setColor(Color.BLACK);
        for(int i=0; i<PicWidth/placeSizeWidth; i++)
        {
            for(int j=0; j<PicHeight/placeSizeHeight; j++)
            {
                g.drawLine( i * placeSizeWidth+3, j * placeSizeHeight,
                        i * placeSizeWidth + placeSizeWidth / 2+3, j * placeSizeHeight);
            }
            g.drawLine( i * placeSizeWidth+3, 0, i * placeSizeWidth+3,
                    (PicHeight / placeSizeHeight) * placeSizeHeight);
        }
    }
    public void sort(){
        places.sort((Comparator<T>) new HarborComparer());
    }
    private int count=0;
    public boolean hasNext(){
        if(count<places.size()){
            return true;
        }
        return false;
    }
    public Vehicle next(){
        count+=1;
        return (Vehicle)getBoat(count-1);
    }
    public void remove(){
        throw new UnsupportedOperationException();
    }
    public Iterator<Vehicle> iterator(){
        count=0;
        return this;
    }
    public void clear(){
        places.clear();
        places=new ArrayList<T>();
    }
}

