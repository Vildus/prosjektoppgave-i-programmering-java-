package io;

import components.*;
import inventory.Inventory;
import inventory.Item;
import inventory.ItemAlreadyExistsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class InventoryRepositoryTest {

    private static Path inventoryRepositoryDirectory;

    @BeforeAll
    static void setupTests() throws IOException {
        inventoryRepositoryDirectory = createTempDirectory();
    }

    @BeforeEach
    void resetInventory() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field instance = Inventory.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    void testSaveInventory() throws ItemAlreadyExistsException {
        Inventory inventory = Inventory.getInstance();

        inventory.addItem(new Item(new GraphicCard("Dell", "XR100", 120), 2.30, 4567));
        inventory.addItem(new Item(new HardDisk("Dell", "HDD200", "SSD", 500), 20.40, 123453));
        inventory.addItem(new Item(new Keyboard("Dell", "Razor", "bluetooth"), 120, 123451));
        inventory.addItem(new Item(new Motherboard("Dell", "FEOKF", "123inches"), 23.50, 1234));
        inventory.addItem(new Item(new Mouse("Dell", "WODK", "bluetooth"), 400, 46565634));
        inventory.addItem(new Item(new PowerSupply("Dell", "WODJ", 2000, 230, 230), 900, 455));
        inventory.addItem(new Item(new Processor("Dell", "EOKR", 2, 123), 199, 99384));
        inventory.addItem(new Item(new RAM("Dell", "EWDMO", 434), 499, 2233));
        inventory.addItem(new Item(new Screen("Dell", "900AWERWA", 1234), 234, 39394));

        try {
            InventoryRepository inventoryRepository = new InventoryRepository(this.inventoryRepositoryDirectory);
            inventoryRepository.save(inventory);
        } catch (IOException e) {
            fail(e);
        }

        assertEquals(9, inventory.getItems().size());
    }

    @Test
    void testReadInventory() {
        Inventory inventory;

        try {
            InventoryRepository inventoryRepository = new InventoryRepository(this.inventoryRepositoryDirectory);
            inventory = inventoryRepository.read();
        } catch (IOException | ClassNotFoundException e) {
            fail(e);
            return;
        }
        assertEquals(9, inventory.getItems().size());
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
