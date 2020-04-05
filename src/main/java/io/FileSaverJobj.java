package io;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileSaverJobj {
    public void saveFile(Path filePath, List<?> componentList) throws IOException {
        OutputStream os = Files.newOutputStream(filePath); // hvorfor bruke den metoden isteden for FileOutputStream os = new FileOutputStream
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(componentList);
        os.close();
        out.close();
    }
}
