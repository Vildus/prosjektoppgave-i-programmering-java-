package purchase;

import inventory.Item;
import purchase.exceptions.ItemAvailableStockException;

import java.util.*;

public class ShoppingBag {

    private static ShoppingBag instance;

    private List<ShoppingBagItem> shoppingBagItems;

    private ShoppingBag() {
        this.shoppingBagItems = new ArrayList<>();
    }

    public static ShoppingBag getInstance() {
        if (instance == null) {
            instance = new ShoppingBag();
        }
        return instance;
    }

    public List<ShoppingBagItem> getShoppingBagItems() {
        return this.shoppingBagItems;
    }

    //addItem will overwrite if the same item is already in shopping bag
    public void addItem(ShoppingBagItem shoppingBagItem) throws ItemAvailableStockException {
        int inStock = shoppingBagItem.getItem().getInStock();
        if (inStock < shoppingBagItem.getQty()) {
            throw new ItemAvailableStockException(shoppingBagItem.getItem().getArticleNumber(), shoppingBagItem.getQty(), inStock);
        }

        int foundIndex = -1;
        for (int i = 0; i < this.shoppingBagItems.size(); i++) {
            if (this.shoppingBagItems.get(i).getArticleNumber() == shoppingBagItem.getItem().getArticleNumber()) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex >= 0) {
            this.shoppingBagItems.set(foundIndex, shoppingBagItem);
        } else {
            this.shoppingBagItems.add(shoppingBagItem);
        }
    }

    public void removeItem(ShoppingBagItem shoppingBagItem) {
        this.shoppingBagItems.remove(shoppingBagItem);
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (ShoppingBagItem shoppingBagItem : this.shoppingBagItems) {
            totalPrice = totalPrice + shoppingBagItem.getTotalPrice();
        }
        return totalPrice;
    }

    public Order createOrder() {
        List<OrderLine> orderLines = new ArrayList<>();
        for (ShoppingBagItem shoppingBagItem : this.shoppingBagItems) {
            OrderLine orderLine = new OrderLine(shoppingBagItem.getArticleNumber(), shoppingBagItem.getQty(), shoppingBagItem.getPrice());
            orderLines.add(orderLine);
            // update inventory item in stock amount
            Item item = shoppingBagItem.getItem();
            item.setInStock(item.getInStock() - shoppingBagItem.getQty());
        }
        return new Order(orderLines, new Date(), Customer.getCurrentCustomerID());
    }

    public void clear() {
        this.shoppingBagItems.clear();
    }
}