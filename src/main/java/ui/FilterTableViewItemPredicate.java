package ui;

import inventory.Item;

import java.util.function.Predicate;

public class FilterTableViewItemPredicate implements Predicate<Item> {

    private String search;


    public FilterTableViewItemPredicate(String search) {
        this.search = search;
    }


    @Override
    public boolean test(Item item) {
        if (item.getComponentCategory().toLowerCase().startsWith(this.search.toLowerCase())) {
            return true;
        }
        try {
            int searchArticleNumber = Integer.parseInt(search);
            if (item.getArticleNumber() == searchArticleNumber) {
                return true;
            }
        } catch (Exception e) {
            //Her er det ikke noe å gjøre fordi det er ikke nødvendigvis en feil. Vil at koden skal fortsette
        }
        if (item.getComponentBrand().toLowerCase().startsWith(this.search.toLowerCase())) {
            return true;
        }
        if (item.getComponentModel().toLowerCase().startsWith(this.search.toLowerCase())) {
            return true;
        }
        try {
            double searchPrice = Double.parseDouble(search);
            if (item.getPrice() == searchPrice) {
                return true;
            }
        } catch (Exception e) {
            //Her er det ikke noe å gjøre fordi det er ikke nødvendigvis en feil. Vil at koden skal fortsette
        }
        try {
            int searchInStock = Integer.parseInt(search);
            if (item.getInStock() == searchInStock) {
                return true;
            }
        } catch (Exception e) {
            //Her er det ikke noe å gjøre fordi det er ikke nødvendigvis en feil. Vil at koden skal fortsette
        }
        return false;
    }
}






