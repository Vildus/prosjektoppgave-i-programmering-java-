package inventory;

import java.util.function.Predicate;

public class InventorySearchFilter implements Predicate<Item> {

    private String search;

    public InventorySearchFilter(String search) {
        this.search = search;
    }

    @Override
    public boolean test(Item item) {
        // category
        if (item.getComponentCategory().toLowerCase().startsWith(this.search.toLowerCase())) {
            return true;
        }

        // article number
        try {
            int searchArticleNumber = Integer.parseInt(search);
            if (item.getArticleNumber() == searchArticleNumber) {
                return true;
            }
        } catch (Exception e) {
            // We want to search more
        }

        // brand
        if (item.getComponentBrand().toLowerCase().startsWith(this.search.toLowerCase())) {
            return true;
        }

        // component model
        if (item.getComponentModel().toLowerCase().startsWith(this.search.toLowerCase())) {
            return true;
        }

        return false;
    }
}






