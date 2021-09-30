import java.awt.*;

public class PremiumMotor extends MediumMotors{
    protected static int boatWidth;

    public PremiumMotor(int boatWidth, int boatHeight){
        super(boatHeight);
        this.boatWidth=boatWidth;
    }

    @Override
    public void drawMotors(Color dopColor, Graphics2D g2d, int startX, int startY){
        super.drawMotors(dopColor, g2d, startX, startY);
        for(int i=0; i<numbMotors.ordinal(); i++){
            g2d.setColor(dopColor);
            int[] pointMotorPX={startX+10, startX, startX+10};
            int[] pointMotorPY={startY+i*boatHeight/3, startY+i*boatHeight/3+boatHeight/6,
                    startY+i*boatHeight/3+boatHeight/3+1};
            g2d.fillPolygon(pointMotorPX, pointMotorPY, 3);
            g2d.setColor(Color.black);
            g2d.drawPolygon(pointMotorPX, pointMotorPY, 3);
        }
    }
}
