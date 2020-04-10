package inventory;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable {

    private ArrayList<Item> items;

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
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