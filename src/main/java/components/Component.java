package components;

public class Component {
    private double price;
    private String type;


    public Component(double price, String type) {
        this.setPrice(price);
        this.setType(type);
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
