import java.awt.*;

public class MediumMotors extends LightMotor{

    @Override
    public void drawMotors(Color dopColor, Graphics g, int startX, int startY){
        super.drawMotors(dopColor, g, startX, startY);
        for(int i=0; i<numbMotors.ordinal(); i++){
            g.setColor(dopColor);
            int[] pointMotorMX={startX+20, startX+10, startX+20};
            int[] pointMotorMY={startY+i*boatHeight/3, startY+i*boatHeight/3+boatHeight/6,
                    startY+i*boatHeight/3+boatHeight/3+1};
            g.fillPolygon(pointMotorMX, pointMotorMY, 3);
            g.setColor(Color.black);
            g.drawPolygon(pointMotorMX, pointMotorMY, 3);
        }
    }
    public MediumMotors(int boatHeight){
        super(boatHeight);
    }
}
