import java.awt.*;

public class Motors {
    private NumbMotors numbMotors;
    private static int boatHeight;
    public void setNumbMotors(int numbMotors){
        this.numbMotors=NumbMotors.values()[numbMotors - (numbMotors-1)/3*3];
    }
    public void drawMotors(Color dopColor, Graphics2D g2d, int startX, int startY){
        for(int i=0; i<numbMotors.ordinal(); i++){
            g2d.setColor(dopColor);
            int[] pointMotorfX={startX+35, startX+5, startX, startX+5, startX+35};
            int[] pointMotorfY={startY+i*boatHeight/3, startY+i*boatHeight/3, startY+i*boatHeight/3+boatHeight/6,
                    startY+i*boatHeight/3+boatHeight/3+1, startY+i*boatHeight/3+boatHeight/3+1};
            g2d.fillPolygon(pointMotorfX, pointMotorfY, 5);
            g2d.setColor(Color.black);
            g2d.drawPolygon(pointMotorfX, pointMotorfY, 5);
        }
    }
    public void Init(int boatHeight){
        this.boatHeight=boatHeight;

    }
}
