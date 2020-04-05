package io;

import purchase.OrderLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class OrderFileOpener {

    public List<OrderLine> readFile(Path filePath) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<OrderLine> items = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(";");
            String articleNumberStr = parts[0];
            int articleNumber = Integer.parseInt(articleNumberStr);
            String amountStr = parts[1];
            int amount = Integer.parseInt(amountStr);
            String pricePerUnitStr = parts[2];
            double pricePerUnit = Double.parseDouble(pricePerUnitStr);
            OrderLine orderLine = new OrderLine(articleNumber, amount, pricePerUnit);
            items.add(orderLine);
        }
        return items;

    }


        /*
        List<String> lines = Files.readAllLines(filePath);
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
    }

         */

}
