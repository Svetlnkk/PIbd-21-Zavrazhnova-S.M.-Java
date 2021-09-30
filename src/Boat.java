import java.awt.*;
import java.util.Random;

public class Boat {
    private int startX;
    private int startY;
    private int picWidth;
    private int picHeight;
    private static final int boatWidth = 158;
    private static final int boatHeight = 78;
    public int MaxSpeed;
    public float Weight;
    public Color MainColor;
    public Color DopColor;
    //палуба катера
    public Boolean Deck;
    //моторы катера
    public Boolean Motor;

    private Motors motors;

    private void setMaxSpeed(int MaxSpeed){
        this.MaxSpeed=MaxSpeed;
    }
    public int getMaxSpeed(){return MaxSpeed;}

    private void setWeight(float Weight){
        this.Weight=Weight;
    }
    public float getWeight(){return Weight;}

     private void setMainColor(Color MainColor){
        this.MainColor=MainColor;
     }
     public Color getMainColor(){return MainColor;}

     public void setDopColor(Color DopColor) {
         this.DopColor = DopColor;
     }
     public Color getDopColor(){return DopColor;}

     private void setDeck(Boolean Deck){
        this.Deck=Deck;
     }
     public Boolean getDeck(){return Deck;}

    private void setMotor(Boolean Motor){this.Motor=Motor;}
    public Boolean getMotor(){return Motor;}


    public void Init(int maxSpeed, float weight, Color mainColor, Color dopColor,
                     Boolean deck, Boolean motor)
    {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        DopColor = dopColor;
        Deck = deck;
        Motor=motor;
        motors=new Motors();
        motors.Init(boatHeight);
        if(motor){
            Random rand = new Random();
            motors.setNumbMotors(rand.nextInt(5)+1);
        }
        else{
            motors.setNumbMotors(0);
        }

    }

    public void SetPosition(int x, int y, int width, int height)
    {
        startX = x;
        startY = y;
        picWidth = width;
        picHeight = height;
    }

    public void MoveTransport(Direction direction)
    {
        float step = MaxSpeed * 100 / Weight;
        switch (direction)
        {
            case Right:
                if (startX + step < picWidth - boatWidth)
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
                if (startY + step < picHeight - boatHeight)
                {
                    startY += step;
                }
                break;
        }
    }

    public void DrawTransport(Graphics g)
    {
        g.clearRect(0, 0, 818, 497);
        Graphics2D g2d=(Graphics2D)g;

        g2d.setPaint(Color.black);

        g2d.setColor(MainColor);
        int[] pointBoatX={startX+20, startX+120,startX+160, startX+120, startX+20};
        int[] pointBoatY={startY, startY, startY+40, startY+80, startY+80};
        g2d.fillPolygon(pointBoatX, pointBoatY, 5);
        g2d.setColor(Color.black);
        g2d.drawPolygon(pointBoatX, pointBoatY, 5);
        g2d.drawOval(startX + 40, startY + 20, 85, 40);

        if (Deck)
        {
            g2d.setColor(DopColor);
            int[] pointDeckX={startX + 120, startX + 120, startX + 150, startX + 120, startX + 120, startX + 159};
            int[] pointDeckY={startY + 1, startY + 10, startY+40, startY+70, startY+79, startY+40};
            g2d.fillPolygon(pointDeckX, pointDeckY, 6);
            g2d.setColor(Color.black);
            g2d.drawPolygon(pointDeckX, pointDeckY, 6);
        }

        if(Motor){
            motors.drawMotors(DopColor, g2d, startX, startY);
        }
    }
}
