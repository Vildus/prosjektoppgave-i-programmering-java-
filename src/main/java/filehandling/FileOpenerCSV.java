package filehandling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileOpenerCSV implements InterfaceFileOpen {
    @Override
    public List<?> readFile(Path filePath) throws IOException, ClassNotFoundException {
        /*List<String> lines = Files.readAllLines(filePath);
        List<Person> persons = new ArrayList<>();

        for (String line : lines) {
            // Vilde;123123 123;+67232323;vilde@test.no
            String[] parts = line.split(";");
            String name = parts[0];
            String birthdateStr = parts[1];
            String phoneNumber = parts[2];
            String email = parts[3];
            Person p = new Person(name, LocalDate.parse(birthdateStr), email, phoneNumber);
            persons.add(p);
        }

        return persons;
    }*/

}
