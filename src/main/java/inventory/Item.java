package inventory;

import components.Component;

import java.io.Serializable;

public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    private Component component;
    private double price;
    private int articleNumber;
    private int inStock;

    public Item(Component component, double price, int articleNumber) {
        this.component = component;
        this.setPrice(price);
        this.articleNumber = articleNumber;
    }

    public String getComponentBrand() {
        return this.component.getBrand();
    }

    public String getComponentModel() {
        return this.component.getModel();
    }

    public String getComponentCategory() {
        return this.component.getCategory();
    }

    public int getInStock() {
        return this.inStock;
    }

    // en måte å sette en stock på!
    public void setInStock(int inStock) {
        if (inStock < 0) {
            throw new InvalidInStockArgumentException("In stock cannot be lower than 0");
        }
        this.inStock = inStock;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new InvalidPriceArgumentException("Price must be above 0");
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
