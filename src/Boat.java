import java.awt.*;
import java.util.Random;

public class Boat extends Skiff{

    public Color DopColor;
    //палуба катера
    public Boolean Deck;
    //моторы катера
    public Boolean Motor;

    private ISpeed motors;

    public void setDopColor(Color DopColor) {
        this.DopColor = DopColor;
    }
    public Color getDopColor(){return DopColor;}

    private void setDeck(Boolean Deck){
        this.Deck=Deck;
    }
    public Boolean getDeck(){return Deck;}

    private void setLightMotor(Boolean Motor){this.Motor=Motor;}
    public Boolean getLightMotor(){return Motor;}

    public Boat(int maxSpeed, float weight, Color mainColor, Color dopColor,
                Boolean deck, Boolean Motor)
    {
        super(maxSpeed, weight, mainColor, 165, 78);
        DopColor = dopColor;
        Deck = deck;
        this.Motor=Motor;
        if(Motor){
            Random rand = new Random();
            switch(rand.nextInt(3)){
                case 0:
                    motors=new LightMotor(skiffHeight);
                    motors.setNumbMotors(rand.nextInt(5)+1);
                    break;
                case 1:
                    motors=new MediumMotors(skiffHeight);
                    motors.setNumbMotors(rand.nextInt(5)+1);
                    break;
                case 2:
                    motors=new PremiumMotor(skiffWidth, skiffHeight);
                    motors.setNumbMotors(rand.nextInt(5)+1);
                    break;
            }
        }
    }

    @Override
    public void DrawTransport(Graphics g)
    {
        super.DrawTransport(g);

        Graphics2D g2d=(Graphics2D)g;
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
