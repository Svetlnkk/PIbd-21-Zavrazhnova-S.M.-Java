import java.awt.*;
public abstract class Vehicle implements ITransport{
    protected int startX;
    protected int startY;
    protected int picWidth;
    protected int picHeight;
    public int MaxSpeed;
    public float Weight;
    public Color MainColor;
    public void setMaxSpeed(int MaxSpeed){
        this.MaxSpeed=MaxSpeed;
    }
    public int getMaxSpeed(){return MaxSpeed;}

    public void setWeight(float Weight){
        this.Weight=Weight;
    }
    public float getWeight(){return Weight;}

    public void setMainColor(Color MainColor){
        this.MainColor=MainColor;
    }
    public Color getMainColor(){return MainColor;}

    public void SetPosition(int x, int y, int width, int height)
    {
        startX = x;
        startY = y;
        picWidth = width;
        picHeight = height;
    }

    public abstract void DrawTransport(Graphics g);
    public abstract void MoveTransport(Direction direction);
}
