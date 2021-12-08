public class HarborOverflowException extends RuntimeException{
    public HarborOverflowException() {
        super( "На гавани нет свободных мест" );
    }
}
