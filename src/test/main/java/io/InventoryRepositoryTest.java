package io;

import components.*;
import inventory.Inventory;
import inventory.Item;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class InventoryRepositoryTest {

    private Inventory createTestInventory() {
        Inventory inventory = new Inventory();


        inventory.addItem(new Item(new GraphicCard("Dell", "XR100", 120), 2.30, 4567));
        inventory.addItem(new Item(new Harddisc("Dell", "HDD200", "SSD"), 20.40, 123453));
        inventory.addItem(new Item(new Keyboard("Dell", "Razor", "bluetooth"), 120, 123451));
        inventory.addItem(new Item(new Motherboard("Dell", "FEOKF", "123inches"), 23.50, 1234));
        inventory.addItem(new Item(new Mouse("Dell", "WODK", "bluetooth"), 400, 46565634));
        inventory.addItem(new Item(new PowerSupply("Dell", "WODJ", 2000, 230, 230), 900, 455));
        inventory.addItem(new Item(new Processor("Dell", "EOKR", 2, 123), 199, 99384));
        inventory.addItem(new Item(new RAM("Dell", "EWDMO", 434), 499, 2233));
        inventory.addItem(new Item(new Screen("Dell", "900AWERWA", 1234), 234, 39394));

        return inventory;
    }

    @Test
    void testSaveInventory() {
        Inventory inventory = this.createTestInventory();

        try {
            Path inventoryRepositoryDirectory = createTempDirectory();
            InventoryRepository inventoryRepository = new InventoryRepository(inventoryRepositoryDirectory);
            inventoryRepository.saveInventory(inventory);
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    void testReadInventory() {
        Inventory inventory1 = this.createTestInventory();
        Inventory inventory2;

        try {
            Path inventoryRepositoryDirectory = createTempDirectory();
            InventoryRepository inventoryRepository = new InventoryRepository(inventoryRepositoryDirectory);
            inventoryRepository.saveInventory(inventory1);
            inventory2 = inventoryRepository.ReadInventory();
        } catch (IOException | ClassNotFoundException e) {
            fail(e);
            return;
        }
        assertEquals(inventory1, inventory2);
    }


    private static Path createTempDirectory() throws IOException {
        Path dir = Files.createTempDirectory("InventoryRepositoryTest");
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(dir)) {
            dir.toFile().deleteOnExit();
            for (Path file : ds) {
                file.toFile().deleteOnExit();
            }
        }
        return dir;
    }
}