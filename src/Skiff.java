import java.awt.*;

public class Skiff extends Vehicle{
    protected static  int skiffWidth = 158;
    protected static  int skiffHeight = 78;
    public Skiff(int maxSpeed, float weight, Color mainColor)
    {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
    }

    protected Skiff(int maxSpeed, float weight, Color mainColor, int skiffWidth, int skiffHeight)
    {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        this.skiffWidth = skiffWidth;
        this.skiffHeight = skiffHeight;
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
}

