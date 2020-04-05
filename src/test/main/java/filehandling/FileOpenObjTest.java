package filehandling;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileOpenObjTest {
    FileOpenerJobj fileOpener = new FileOpenerJobj();
    List<?> componentList;
        try {
        componentList = fileOpener.readFile(filePath);
    } catch (IOException e) {
        fail(e);
        return;
    }

}