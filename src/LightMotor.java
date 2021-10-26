import java.awt.*;

public class LightMotor implements ISpeed{
    protected NumbMotors numbMotors;
    protected static int boatHeight;
    protected static int boatWidth;

    public LightMotor(int boatHeight){
        this.boatHeight=boatHeight;
    }
    public void setNumbMotors(int numbMotors){
        this.numbMotors=NumbMotors.values()[numbMotors - (numbMotors-1)/3*3];
    }
    public void drawMotors(Color dopColor, Graphics g, int startX, int startY){
        for(int i=0; i<numbMotors.ordinal(); i++){
            g.setColor(dopColor);
            int[] pointMotorfX={startX+45, startX+20, startX+20, startX+45};
            int[] pointMotorfY={startY+i*boatHeight/3, startY+i*boatHeight/3,
                    startY+i*boatHeight/3+boatHeight/3+1, startY+i*boatHeight/3+boatHeight/3+1};
            g.fillPolygon(pointMotorfX, pointMotorfY, 4);
            g.setColor(Color.black);
            g.drawPolygon(pointMotorfX, pointMotorfY, 4);
        }
    }
}
