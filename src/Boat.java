import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Boat extends Skiff{

    public Color DopColor;
    //палуба катера
    public boolean Deck;
    //моторы катера
    public boolean Motor;

    private ISpeed motors;

    public void setDopColor(Color DopColor) {
        this.DopColor = DopColor;
    }
    public Color getDopColor(){return DopColor;}

    private void setDeck(boolean Deck){
        this.Deck=Deck;
    }
    public boolean getDeck(){return Deck;}

    private void setLightMotor(boolean Motor){this.Motor=Motor;}
    public boolean getLightMotor(){return Motor;}

    public Boat(int maxSpeed, int weight, Color mainColor, Color DopColor,
                boolean Deck, boolean Motor)
    {
        super(maxSpeed, weight, mainColor, 165, 78);
        this.DopColor = DopColor;
        this.Deck = Deck;
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
        else{
            motors=new LightMotor();
            motors.setNumbMotors(0);
        }
    }
    public Boat(String info) {
        super(info);
        String[] strs = info.split( String.valueOf( separator ) );
        if (strs.length == 8) {
            MaxSpeed = Integer.parseInt( strs[0] );
            Weight = Integer.parseInt( strs[1] );
            MainColor = Color.decode( strs[2] );
            DopColor = Color.decode( strs[3] );
            Deck=Boolean.parseBoolean(strs[4]);
            Motor=Boolean.parseBoolean(strs[5]);
            if(Objects.equals(strs[6], "LightMotor")){
                motors=new LightMotor();
            }
            if(Objects.equals(strs[6], "MediumMotors")){
                motors=new MediumMotors();
            }
            if(Objects.equals(strs[6], "PremiumMotor")){
                motors=new PremiumMotor();
            }
            motors.Init(skiffWidth, skiffHeight);
            motors.setNumbMotors(Integer.parseInt(strs[7]));
        }
    }
    @Override
    public String toString() {
        return String.valueOf(MaxSpeed) + String.valueOf(separator) + String.valueOf(Weight) + String.valueOf(separator) + String.valueOf(MainColor.getRGB())
                + String.valueOf(separator) + String.valueOf(DopColor.getRGB()) + String.valueOf(separator) +
                String.valueOf(Deck) + String.valueOf(separator) + String.valueOf(Motor) + String.valueOf(separator) + motors.getName() + String.valueOf(separator) + motors.getNumbMotors();
    }
    public void setMotors(ISpeed Motors){
        motors=Motors;
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
