package inventory;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import ui.FilterTableViewItemPredicate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Inventory implements Serializable {

    private List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        // TODO: check if article number exists - and if it does - throw ArticleNumberAlreadyExistsException
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

}

//TODO: Ikke kunne legge til to varer med samme artikkelnummer