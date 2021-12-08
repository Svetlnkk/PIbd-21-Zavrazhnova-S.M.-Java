public class HarborNotFoundException extends RuntimeException{
    public HarborNotFoundException(int i) {
        super( "Не найдена лодка по месту " + i );
    }
}
