package filehandling;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileOpenerJobj implements InterfaceFileOpen {
    @Override
    public List<?> readFile(Path filePath) throws IOException, ClassNotFoundException {
        InputStream in = Files.newInputStream(filePath);
        ObjectInputStream oin = new ObjectInputStream(in);
        Object component = oin.readObject();
        return (List<?>) component; //må caste
    }
}
