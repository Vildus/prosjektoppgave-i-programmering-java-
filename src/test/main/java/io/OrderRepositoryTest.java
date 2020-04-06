package io;

import org.junit.jupiter.api.Test;
import purchase.Order;
import purchase.OrderLine;
import purchase.OrderRegister;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {

    private OrderRegister createTestOrderRegister() {
        OrderRegister orderRegister = new OrderRegister();

        ArrayList<OrderLine> order1Lines = new ArrayList<>();
        order1Lines.add(new OrderLine(234435, 5, 1560.90));
        order1Lines.add(new OrderLine(4362442, 1, 212.90));
        order1Lines.add(new OrderLine(35632, 12, 187.90));
        Order order1 = new Order(1, new Date(), order1Lines);
        orderRegister.addOrder(order1);

        ArrayList<OrderLine> order2Lines = new ArrayList<>();
        order2Lines.add(new OrderLine(7483719, 2, 100.50));
        order2Lines.add(new OrderLine(7483720, 7, 292.42));
        order2Lines.add(new OrderLine(7483721, 3, 287.90));
        Order order2 = new Order(2, new Date(), order2Lines);
        orderRegister.addOrder(order2);

        return orderRegister;
    }

    @Test
    void testSaveOrderRegister() {
        OrderRegister orderRegister = this.createTestOrderRegister();

        try {
            Path orderRepositoryDirectory = createTempDirectory();
            OrderRepository orderRepository = new OrderRepository(orderRepositoryDirectory);
            orderRepository.saveOrderRegister(orderRegister);
        } catch (Exception ex) {
            fail(ex);
        }
    }

    @Test
    void testReadOrderRegister() {
        OrderRegister orderRegister1 = this.createTestOrderRegister();
        OrderRegister orderRegister2;

        try {
            Path orderRepositoryDirectory = createTempDirectory();
            OrderRepository orderRepository = new OrderRepository(orderRepositoryDirectory);
            orderRepository.saveOrderRegister(orderRegister1);
            orderRegister2 = orderRepository.readOrderRegister();
        } catch (Exception ex) {
            fail(ex);
            return;
        }

        assertEquals(orderRegister1.toString(), orderRegister2.toString());
    }

    // this method will create a temporary directory and "mark it" for deletion
    // after the JVM exits, see: https://docs.oracle.com/javase/7/docs/api/java/io/File.html#deleteOnExit()
    private static Path createTempDirectory() throws IOException {
        Path dir = Files.createTempDirectory("OrderRepositoryTest");
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(dir)) {
            dir.toFile().deleteOnExit();
            for (Path file : ds) {
                file.toFile().deleteOnExit();
            }
        }
        return dir;
    }
}

