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
        if ((""+item.getArticleNumber()).startsWith(search)) {
            return true;
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






