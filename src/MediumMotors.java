import java.awt.*;

public class MediumMotors implements ISpeed{
    protected NumbMotors numbMotors;
    private static int boatHeight;
    private static int boatWidth;
    protected static int numb;

    public void setNumbMotors(int numbMotors){
        this.numbMotors=NumbMotors.values()[numbMotors - (numbMotors-1)/3*3];
        numb=numbMotors;
    }

    public void drawMotors(Color dopColor, Graphics g, int startX, int startY){

        for(int i=0; i<numbMotors.ordinal(); i++){
            g.setColor(dopColor);
            int[] pointMotorX={startX+20,startX+10, startX+20,startX+45,startX+45};
            int[] pointMotorY={startY+i*boatHeight/3,startY+i*boatHeight/3+boatHeight/6,startY+i*boatHeight/3+boatHeight/3+1,
                    startY+i*boatHeight/3+boatHeight/3+1,startY+i*boatHeight/3};
            g.fillPolygon(pointMotorX, pointMotorY, 5);
            g.setColor(Color.black);
            g.drawPolygon(pointMotorX, pointMotorY, 5);
            g.drawLine(startX+20,startY+i*boatHeight/3, startX+20, startY+i*boatHeight/3+boatHeight/3+1);
        }
    }
    public String getName(){
        return "MediumMotors";
    }
    public int getNumbMotors(){
        return numb;
    }
    public void Init(int boatWidth, int boatHeight){
        this.boatWidth = boatWidth;
        this.boatHeight = boatHeight;
    }
}
