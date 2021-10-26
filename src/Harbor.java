import java.awt.*;

public class Harbor<T extends ITransport, U extends ISpeed> {
    private Object[] places;
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
        places =new Object[width * height];
        PicWidth = picWidth;
        PicHeight = picHeight;
    }

    public int addSkiff(T skiff){
        int i=0;
        while(i<places.length && places[i]!=null){
            i++;
        }
        if(i<places.length && places[i]==null){
            skiff.SetPosition(5 + i%width * placeSizeWidth, 5 + i /width* placeSizeHeight,
                    PicWidth, PicHeight);
            places[i] = skiff;
            return i;
        }
        else {
            return -1;
        }
    }

    public T remove(int index){
        if(index>=places.length || index < 0) { return null; }
        if (places[index] != null)
        {
            T return_T = (T)places[index];
            places[index] = null;
            return return_T;
        }
        else { return null; }
    }

    public boolean equal(int numBoat) { // ==
        int numCorrectBoat = 0;
        for (int i = 0; i < places.length; i++) {
            if (places[i] != null) {
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
        for(int i=0; i<places.length; i++)
        {
            if (places[i] != null) {
                T place = (T) places[i];
                place.DrawTransport( g );
            }
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

