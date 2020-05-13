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

    public double getTotalPrice() {
        return this.pricePerUnit * this.amount;
    }

    @Override
    public String toString() {
        return String.format("Article number: %d, amount: %d, price per unit: %.2f",
                this.articleNumber,
                this.amount,
                this.pricePerUnit);
    }
}
