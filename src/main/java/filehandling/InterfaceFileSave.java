package filehandling;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

//? = har ikke spesifisert hva listen består av enda, derfor står det spørsmålstegn

public interface InterfaceFileSave {
    void saveFile(Path filePath, List<?> componentList) throws IOException;

}
