package inventory;

public class InvalidPriceArgumentException extends IllegalArgumentException{

    public InvalidPriceArgumentException(String message) {
        super("Invalid Price" + message); //Her kan man skrive senere: Pris må være over 0. Pris kan
        //ikke inneholde bokstaver
    }
}
