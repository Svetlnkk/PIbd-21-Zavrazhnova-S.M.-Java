import java.awt.*;

public class MediumMotors extends LightMotor{

    @Override
    public void drawMotors(Color dopColor, Graphics2D g2d, int startX, int startY){
        super.drawMotors(dopColor, g2d, startX, startY);
        for(int i=0; i<numbMotors.ordinal(); i++){
            g2d.setColor(dopColor);
            int[] pointMotorMX={startX+20, startX+10, startX+20};
            int[] pointMotorMY={startY+i*boatHeight/3, startY+i*boatHeight/3+boatHeight/6,
                    startY+i*boatHeight/3+boatHeight/3+1};
            g2d.fillPolygon(pointMotorMX, pointMotorMY, 3);
            g2d.setColor(Color.black);
            g2d.drawPolygon(pointMotorMX, pointMotorMY, 3);
        }
    }
    public MediumMotors(int boatHeight){
        super(boatHeight);
    }
}
