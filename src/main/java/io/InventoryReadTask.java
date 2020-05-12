package io;

import inventory.Inventory;
import javafx.concurrent.Task;

public class InventoryReadTask extends Task<Inventory> {

    @Override
    protected Inventory call() throws Exception {
        try {
            //Emulating opening large file
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        InventoryRepository inventoryRepository = new InventoryRepository();
        return inventoryRepository.read();
    }
}

