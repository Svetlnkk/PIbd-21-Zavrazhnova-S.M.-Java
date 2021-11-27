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
                    motors=new LightMotor();
                    motors.setNumbMotors(rand.nextInt(5)+1);
                    break;
                case 1:
                    motors=new MediumMotors();
                    motors.setNumbMotors(rand.nextInt(5)+1);
                    break;
                case 2:
                    motors=new PremiumMotor();
                    motors.setNumbMotors(rand.nextInt(5)+1);
                    break;
            }
            motors.Init(skiffWidth, skiffHeight);
        }
    }
    public void setMotors(ISpeed motors){
        this.motors=motors;
        motors.Init(skiffWidth, skiffHeight);
        Random rand =new Random();
        if(Motor){
            motors.setNumbMotors(rand.nextInt(5)+1);
        }
        else{
            motors.setNumbMotors(0);
        }
    }
    @Override
    public void DrawTransport(Graphics g)
    {
        super.DrawTransport(g);

        if (Deck)
        {
            g.setColor(DopColor);
            int[] pointDeckX={startX + 120, startX + 120, startX + 150, startX + 120, startX + 120, startX + 159};
            int[] pointDeckY={startY + 1, startY + 10, startY+40, startY+70, startY+79, startY+40};
            g.fillPolygon(pointDeckX, pointDeckY, 6);
            g.setColor(Color.black);
            g.drawPolygon(pointDeckX, pointDeckY, 6);
        }

        if(Motor){
            motors.drawMotors(DopColor, g, startX, startY);
        }
    }
}
