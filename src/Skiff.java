import java.awt.*;

public class Skiff extends Vehicle{
    protected static  int skiffWidth = 158;
    protected static  int skiffHeight = 78;
    protected char separator = ';';
    public Skiff(int maxSpeed, int weight, Color mainColor)
    {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
    }

    protected Skiff(int maxSpeed, int weight, Color mainColor, int skiffWidth, int skiffHeight)
    {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        this.skiffWidth = skiffWidth;
        this.skiffHeight = skiffHeight;
    }
    public Skiff(String info) {
        String[] strs = info.split( String.valueOf( separator ) );
        if (strs.length == 3) {
            MaxSpeed = Integer.parseInt( strs[0] );
            Weight = Integer.parseInt( strs[1] );
            MainColor = Color.decode( strs[2] );
        }
    }
    @Override
    public void MoveTransport(Direction direction)
    {
        float step = MaxSpeed * 100 / Weight;
        switch (direction)
        {
            case Right:
                if (startX + step < picWidth - skiffWidth)
                {
                    startX += step;
                }
                break;
            case Left:
                if(startX-step>0)
                {
                    startX -= step;
                }
                break;
            case Up:
                if(startY - step > 0)
                {
                    startY -= step;
                }
                break;
            case Down:
                if (startY + step < picHeight - skiffHeight)
                {
                    startY += step;
                }
                break;
        }
    }
    @Override
    public void DrawTransport(Graphics g)
    {
        g.setColor(Color.black);

        g.setColor(MainColor);
        int[] pointBoatX={startX+20, startX+120,startX+160, startX+120, startX+20};
        int[] pointBoatY={startY, startY, startY+40, startY+80, startY+80};
        g.fillPolygon(pointBoatX, pointBoatY, 5);
        g.setColor(Color.black);
        g.drawPolygon(pointBoatX, pointBoatY, 5);
        g.drawOval(startX + 40, startY + 20, 85, 40);
    }
    @Override
    public String toString() {
        return String.valueOf(MaxSpeed) + String.valueOf(separator) + String.valueOf(Weight) + String.valueOf(separator) + String.valueOf(MainColor.getRGB());
    }
}

