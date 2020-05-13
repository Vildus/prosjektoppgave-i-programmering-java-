package io;

import inventory.Inventory;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class InventoryRepository {
    private Path directory;

    public InventoryRepository(Path directory) {
        this.directory = directory;
    }

    public InventoryRepository() throws IOException {
        String cwd = System.getProperty("user.dir");
        Path storePath = Paths.get(cwd, "datastore");
        try {
            Files.createDirectory(storePath);
        } catch (FileAlreadyExistsException e) {
            // This is ok - datastore dir already created
        }
        this.directory = storePath;
    }

    private Path getInventoryPath() {
        return Paths.get(this.directory.toString(), "inventory.jobj");
    }

    public void save(Inventory inventory) throws IOException {
        Path path = this.getInventoryPath();
        OutputStream os = Files.newOutputStream(path);
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(inventory);
        os.close();
        out.close();
    }

    public Inventory read() throws IOException, ClassNotFoundException {
        Path path = this.getInventoryPath();
        if (Files.exists(path) == false) {
            throw new FileNotFoundException(path.toString());
        }
        InputStream in = Files.newInputStream(path);
        ObjectInputStream oin = new ObjectInputStream(in);
        return (Inventory) oin.readObject();
    }
}
