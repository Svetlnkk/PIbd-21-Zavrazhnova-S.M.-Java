import java.util.Comparator;

public class HarborComparer implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle x, Vehicle y){
        if(x.getClass()==y.getClass()){
            if(x.getClass()==Skiff.class){
                return ComparerSkiff((Skiff)x, (Skiff)y);
            }
            else{
                return ComparerBoat((Boat)x,(Boat)y);
            }

        }
        else{
            if(x.getClass()==Skiff.class){
                return 1;
            }
            else{
                return -1;
            }
        }
    }
    private int ComparerSkiff(Skiff x, Skiff y){
        if (x.MaxSpeed != y.MaxSpeed) {
            return Integer.compare(x.MaxSpeed, y.MaxSpeed);
        }
        if (x.Weight != y.Weight) {
            return Integer.compare(x.Weight, y.Weight);
        }
        if (x.MainColor != y.MainColor) {
            return Integer.compare(x.MainColor.getRGB(), y.MainColor.getRGB());
        }
        return 0;
    }
    private int ComparerBoat(Boat x, Boat y){
        var res = ComparerSkiff(x, y);
        if (res != 0) {
            return res;
        }
        if (x.DopColor != y.DopColor) {
            return Integer.compare(x.DopColor.getRGB(), y.DopColor.getRGB());
        }
        if (x.Deck != y.Deck) {
            return Boolean.compare(x.Deck, y.Deck);
        }
        if (x.Motor != y.Motor) {
            return Boolean.compare(x.Motor, y.Motor);
        }
        return 0;
    }
}
