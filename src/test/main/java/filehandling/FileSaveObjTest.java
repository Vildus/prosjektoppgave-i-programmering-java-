package filehandling;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileSaveObjTest {

    @Test
    void saveFile() {
        ArrayList<?> componentList = new ArrayList<>();

        componentList.add(new ?(//data));
        componentList.add(new ?(//data));
        componentList.add(new ?(//data));

        Path filePath = Path.of(URI.create(/*path location*/""));
        FileSaveObj fileSaver = new FileSaveObj();
        try {
            fileSaver.saveFile(filePath, componentList);
        } catch (IOException e) {
            fail(e);
            return;
        }
    }
}