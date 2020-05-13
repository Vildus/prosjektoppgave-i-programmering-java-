package purchase;

import inventory.Item;

public class ShoppingBagItem {
    private Item item;
    private int qty;

    public ShoppingBagItem(Item item, int qty) {
        this.item = item;
        this.qty = qty;
    }

    public Item getItem() {
        return this.item;
    }

    public String getComponentBrand() {
        return this.item.getComponentBrand();
    }

    public String getComponentModel() {
        return this.item.getComponentModel();
    }

    public String getComponentCategory() {
        return this.item.getComponentCategory();
    }

    public int getArticleNumber() {
        return this.item.getArticleNumber();
    }

    public double getPrice() {
        return this.item.getPrice();
    }

    public double getTotalPrice() {
        return this.qty * this.item.getPrice();
    }

    public int getQty() {
        return this.qty;
    }
}
