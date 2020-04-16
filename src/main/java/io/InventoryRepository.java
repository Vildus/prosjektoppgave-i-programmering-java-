package io;

import inventory.Inventory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


public class InventoryRepository {
    private Path directory;

    public InventoryRepository(Path directory) {
        this.directory = directory;
    }

    private Path getInventoryPath() {
        return Path.of(this.directory.toString(), "inventory.jobj");
    }

    public void saveInventory(Inventory inventory) throws IOException {
        Path path = this.getInventoryPath();
        OutputStream os = Files.newOutputStream(path);
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(inventory);
        os.close();
        out.close();
    }

    public Inventory ReadInventory() throws IOException, ClassNotFoundException {
        Path path = this.getInventoryPath();
        InputStream in = Files.newInputStream(path);
        ObjectInputStream oin = new ObjectInputStream(in);
        Object inventory = oin.readObject();
        return (Inventory) inventory;
    }
}