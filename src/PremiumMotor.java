import java.awt.*;

public class PremiumMotor extends MediumMotors{
    protected static int boatWidth;
    public PremiumMotor(int boatWidth, int boatHeight){
        super(boatHeight);
        this.boatWidth=boatWidth;
    }

    @Override
    public void drawMotors(Color dopColor, Graphics g, int startX, int startY){
        super.drawMotors(dopColor, g, startX, startY);
        for(int i=0; i<numbMotors.ordinal(); i++){
            g.setColor(dopColor);
            int[] pointMotorPX={startX+10, startX, startX+10};
            int[] pointMotorPY={startY+i*boatHeight/3, startY+i*boatHeight/3+boatHeight/6,
                    startY+i*boatHeight/3+boatHeight/3+1};
            g.fillPolygon(pointMotorPX, pointMotorPY, 3);
            g.setColor(Color.black);
            g.drawPolygon(pointMotorPX, pointMotorPY, 3);
        }
    }
}
