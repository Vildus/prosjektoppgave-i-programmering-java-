package inventory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inventory implements Serializable {

    private List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public List<Item> getItems() {
        return this.items;
    }

    public Item findItemByArticleNumber(int articleNumber) {
        for (Item item : this.items) {
            if (item.getArticleNumber() == articleNumber) {
                return item;
            }
        }
        return null;
    }
}

//TODO: Ikke kunne legge til to varer med samme artikkelnummer