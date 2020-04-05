package io;

import inventory.Item;
import purchase.OrderLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class OrderFileSaver implements InterfaceFileSaver {

    @Override
    public <E> void saveFile(Path filePath, List<E> items) throws IOException {
        Files.write(filePath, this.toCSV((ArrayList<OrderLine>) items).getBytes("utf-8"));
    }


    public String toCSV(ArrayList<OrderLine> lines) {
        String output = "";
        for (OrderLine orderLine : lines) {
            output = output + String.format("%s;%s;%s\n", orderLine.getArticleNumber(), orderLine.getAmount(), orderLine.getPricePerUnit());
        }
        return output;
    }

}
