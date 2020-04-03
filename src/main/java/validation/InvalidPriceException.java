package validation;

public class InvalidPriceException extends IllegalArgumentException{

    public InvalidPriceException(String message) {
        super("Invalid Price" + message); //Her kan man skrive senere: Pris må være over 0. Pris kan
        //ikke inneholde bokstaver
    }
}
