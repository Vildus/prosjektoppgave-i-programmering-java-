package filehandling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileSaverCSV implements InterfaceFileSave {
    @Override
    public void saveFile(Path filePath, List<?> componentList) throws IOException {
   /*     Files.write(filePath, this.toCSV(componentList).getBytes("utf-8"));
    }

    public String toCSV(List<?> componentList) {
        String output = "";
        for (Person person : persons) {
            //output = output + String.format("%s;%s;%s;%s\n", person.getName(), person.getBirthday().toString(), person.getPhoneNumber(), person.getEmail());
        }
        return output;
    }*/
}
