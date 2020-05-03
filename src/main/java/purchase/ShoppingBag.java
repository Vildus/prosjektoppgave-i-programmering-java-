package purchase;

import inventory.Inventory;
import inventory.Item;

import java.util.*;

public class ShoppingBag {
    //Starte med en tom shoppingbag. Når en ny bruker kommer inn lager man en ny bag for den brukeren
    //Når antall går til 0.. må være noe logikk som fjerner item fra shopping bag


    //Hva om det er flere brukere som handler samtidig?
    //Denne logikken kan vi ta senere. Eller skal vi tillate kun en bruker som shopper samtidig
    // da slipper vi flere shoppingbag instanser og problem med varebeholdning som vi må skrive logikk for.
    // Da må vi skrive dette i oppgaven: Dette programmet tillater kun en som handler om gangen


    private List<ShoppingBagItem> shoppingBagItems;
    private Inventory inventory; //tilgang til varelager

    public ShoppingBag(Inventory inventory) {
        this.shoppingBagItems = new ArrayList<>();
        this.inventory = inventory;
        //tom liste
    }

    public List<ShoppingBagItem> getShoppingBagItems() {
        return this.shoppingBagItems;
    }

//addItem will overwrite if the same item is already added
    public void addItem(ShoppingBagItem shoppingBagItem) throws ItemAvailableStockException {
        int inStock = shoppingBagItem.getItem().getInStock();
        if (inStock < shoppingBagItem.getQty()) {
            throw new ItemAvailableStockException(shoppingBagItem.getItem().getArticleNumber(), shoppingBagItem.getQty(), inStock);
        }

        int foundIndex = -1; //dette er indeksen til det item som har samme artikkelnummer som det som kommer inn
        for (int i = 0; i < this.shoppingBagItems.size(); i++) {
            if (this.shoppingBagItems.get(i).getArticleNumber() == shoppingBagItem.getItem().getArticleNumber()) {
                foundIndex = i;
                break;
            }

        }

        //Hvis founIndex er 0 eller mer så inneholder den en index til det item som er lik det som sendes inn
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
            totalPrice = totalPrice + shoppingBagItem.getPrice() * shoppingBagItem.getQty();
        }
        return totalPrice;
    }


}