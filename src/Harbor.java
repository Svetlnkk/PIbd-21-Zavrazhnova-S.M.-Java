import java.awt.*;
import java.util.ArrayList;

public class Harbor<T extends ITransport, U extends ISpeed> {
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
    public T getBoat(int index){
        if(index>-1 && index<places.size()){
            return places.get(index);
        }
        return null;
    }

    public int addSkiff(T skiff){
        if(places.size()<maxCount){
            places.add(skiff);
            return places.size()-1;
        }
        else {
            return -1;
        }
    }

    public T remove(int index){
        if(index>-1 && index<places.size()){
            T removeSkiff=places.get(index);
            places.remove(index);
            return removeSkiff;
        }
        else { return null; }
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
}

