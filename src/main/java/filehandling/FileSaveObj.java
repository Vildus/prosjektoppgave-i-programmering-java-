package filehandling;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileSaveObj implements InterfaceFileSave {
    @Override
    public void saveFile(Path filePath, List<?> componentList) throws IOException {
        OutputStream os = Files.newOutputStream(filePath);
        ObjectOutputStream out = new ObjectOutputStream(os);
        //ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath)); hva er forskjellen her, ser mange gjør det på turtorial
        out.writeObject(componentList);
        os.close();
        out.close();

    }
}
