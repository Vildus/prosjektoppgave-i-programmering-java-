package purchase;


public class OrderLine {
    private int articleNumber;
    private int amount;
    private double pricePerUnit;

    public OrderLine(int articleNumber, int amount, double pricePerUnit) {
        this.articleNumber = articleNumber;
        this.amount = amount;
        this.pricePerUnit = pricePerUnit;
    }

    public int getArticleNumber() {
        return this.articleNumber;
    }

    public int getAmount() {
        return this.amount;
    }

    public double getPricePerUnit() {
        return this.pricePerUnit;
    }

    //inneholder flere ordrelinjer
    //en ordrelinje må ha en referanse til artikkelnummeret som er kjøpt (art nummer er unikt)
    //en dato når kjøpt
    //en liste over purchased items
}
