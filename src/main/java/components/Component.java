package components;

public abstract class Component {
    private String brand;
    private String model;
    private double price;
    private int articleNumber;



    public Component(String brand, String model, double price, int articleNumber) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.articleNumber = articleNumber;
    }

    public abstract String getCategory();

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getArticleNumber() {
        return this.articleNumber;
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber = articleNumber;
    }
}
