package filehandling;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface InterfaceFileOpen {
    List<?> readFile(Path filePath) throws IOException, ClassNotFoundException;
}
