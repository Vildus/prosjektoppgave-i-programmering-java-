package io;

import org.junit.jupiter.api.Test;
import purchase.OrderLine;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderFileTest {

    @Test
    void testFileCSV() {
        ArrayList<OrderLine> orderLines = new ArrayList<>();

        orderLines.add(new OrderLine(7483719, 2, 100.90));
        orderLines.add(new OrderLine(7483720, 7, 292.90));
        orderLines.add(new OrderLine(7483721, 3, 287.90));


        Path filePath = Path.of(URI.create("file:///Users/vilderoksandfauchald/Desktop/ordre.csv"));
        OrderFileSaver fileSaver = new OrderFileSaver();
        try {
            fileSaver.saveFile(filePath, orderLines);
        } catch (IOException e) {
            fail(e);
            return;
        }

        OrderFileOpener fileOpener = new OrderFileOpener();
        ArrayList<OrderLine> orderLines2;
        try {
            orderLines2 = (ArrayList<OrderLine>) fileOpener.readFile(filePath);
        } catch (IOException | ClassNotFoundException e) {
            fail(e);
            return;
        }

        System.out.println(fileSaver.toCSV(orderLines));

        assertEquals(fileSaver.toCSV(orderLines), fileSaver.toCSV(orderLines2));

    }
}

