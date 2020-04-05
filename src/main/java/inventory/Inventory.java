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
}
