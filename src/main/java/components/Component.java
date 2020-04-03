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

}
