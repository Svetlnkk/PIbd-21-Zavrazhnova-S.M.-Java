import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

public class Boat extends Skiff implements Comparable<Object>, Iterable<Object>, Iterator<Object> {

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
    protected LinkedList<Object> listBoatProperties = new LinkedList<>();
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
        listBoatProperties.add(MaxSpeed);
        listBoatProperties.add(Weight);
        listBoatProperties.add(MainColor);
        listBoatProperties.add(DopColor);
        listBoatProperties.add(Motor);
        listBoatProperties.add(motors);
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
        listBoatProperties.add(MaxSpeed);
        listBoatProperties.add(Weight);
        listBoatProperties.add(MainColor);
        listBoatProperties.add(DopColor);
        listBoatProperties.add(Motor);
        listBoatProperties.add(motors);
    }
    @Override
    public String toString() {
        return String.valueOf(MaxSpeed) + String.valueOf(separator) + String.valueOf(Weight) + String.valueOf(separator) + String.valueOf(MainColor.getRGB())
                + String.valueOf(separator) + String.valueOf(DopColor.getRGB()) + String.valueOf(separator) +
                String.valueOf(Deck) + String.valueOf(separator) + String.valueOf(Motor) + String.valueOf(separator) + motors.getName() + String.valueOf(separator) + motors.getNumbMotors();
    }
    public boolean equals(Boat other) {
        if (other == null) {
            return false;
        }
        if (other.getClass() != Boat.class) {
            return false;
        }
        if (MaxSpeed != other.MaxSpeed) {
            return false;
        }
        if (Weight != other.Weight) {
            return false;
        }
        if (MainColor != other.MainColor) {
            return false;
        }
        if (DopColor != other.DopColor) {
            return false;
        }
        if (Deck != other.Deck) {
            return false;
        }
        if (Motor != other.Motor) {
            return false;
        }
        if (motors.getClass() != other.motors.getClass()){
            return false;
        }
        if(motors.getNumbMotors()!=other.motors.getNumbMotors()){
            return false;
        }
        return true;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != Boat.class){
            return false;
        }
        else {
            return equals((Boat)obj);
        }
    }
    @Override
    public int compareTo(Object obj) {
        if (obj == null) {
            return -1;
        }
        if (obj.getClass() != Skiff.class) {
            return -1;
        }
        Boat other = (Boat) obj;
        if (MaxSpeed != other.MaxSpeed) {
            return Integer.compare(MaxSpeed, other.MaxSpeed);
        }
        if (Weight != other.Weight) {
            return Integer.compare(Weight, other.Weight);
        }
        if (MainColor != other.MainColor) {
            return Integer.compare(MainColor.getRGB(), other.MainColor.getRGB());
        }if (DopColor != other.DopColor) {
            return Integer.compare(DopColor.getRGB(), other.DopColor.getRGB());
        }
        if (Deck != other.Deck) {
            return Boolean.compare(Deck, other.Deck);
        }
        if (Motor != other.Motor) {
            return Boolean.compare(Motor, other.Motor);
        }
        return 0;
    }
    private int count = 0;
    public boolean hasNext(){
        if (count < listBoatProperties.size()){
            return true;
        }
        return false;
    }
    public Object next(){
        count += 1;
        return listBoatProperties.get(count);
    }
    public void remove(){}
    public Iterator<Object> iterator(){
        count = 0;
        return listBoatProperties.iterator();
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
