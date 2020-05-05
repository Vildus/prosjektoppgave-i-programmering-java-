package inventory;

import ui.FilterTableViewItemPredicate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Inventory INSTANCE = new Inventory();

    private List<Item> items;

    private Inventory() {
        this.items = new ArrayList<>();
    }

    public static Inventory getInstance() {
        return INSTANCE;
    }

    public void addItem(Item item) throws ItemAlreadyExistsException {
        if (this.findItemByArticleNumber(item.getArticleNumber()) != null) {
            throw new ItemAlreadyExistsException(item.getArticleNumber());
        }
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


    public List<Item> filter(String search) {
        //Leser strømmen til strømmen ikke returnerer noe mer og konverter til obeservable arraylist
        Stream<Item> filteredStream = items.stream().filter(new FilterTableViewItemPredicate(search));
        return filteredStream.collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Item> getItemsByComponentCategory(String category) {
        List<Item> itemsByCategory = new ArrayList<>();
        for (Item item : this.items) {
            if (item.getComponentCategory().equals(category)) {
                itemsByCategory.add(item);
            }
        }
        return itemsByCategory;
    }
}

//TODO: Ikke kunne legge til to varer med samme artikkelnummer