package inventory;

import components.Component;
import validation.InvalidPriceException;
import java.io.Serializable;

public class Item implements Serializable {

    private Component component;
    private double price;
    private int articleNumber;
    private int inStock;

    public Item(Component component, double price, int articleNumber) {
        this.component = component;
        this.setPrice(price);
        this.articleNumber = articleNumber;
    }

    public int getInStock() {
        return this.inStock;
    }

    // en måte å sette en stock på!
    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new InvalidPriceException("Price must be above 0");
        }
        this.price = price;
    }

    public int getArticleNumber() {
        return this.articleNumber;
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber = articleNumber;
    }

}
