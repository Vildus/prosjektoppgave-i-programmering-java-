package purchase;

import org.junit.jupiter.api.Test;
import purchase.exceptions.IllegalCustomerIDException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testValidCustomerID() {
        Customer.setCurrentCustomerID("customer1");
        assertEquals("customer1", Customer.getCurrentCustomerID());
        Customer.setCurrentCustomerID("12345");
        assertEquals("12345", Customer.getCurrentCustomerID());
        Customer.setCurrentCustomerID("customer2");
        assertEquals("customer2", Customer.getCurrentCustomerID());
    }

    @Test
    void testInvalidCustomerID() {
        assertThrows(
                IllegalCustomerIDException.class,
                () -> {
                    Customer.setCurrentCustomerID("123");
                });
        assertThrows(
                IllegalCustomerIDException.class,
                () -> {
                    Customer.setCurrentCustomerID("abc");
                });
        assertThrows(
                IllegalCustomerIDException.class,
                () -> {
                    Customer.setCurrentCustomerID("1bc");
                });
        assertThrows(
                IllegalCustomerIDException.class,
                () -> {
                    Customer.setCurrentCustomerID(" ");
                });
        assertThrows(
                IllegalCustomerIDException.class,
                () -> {
                    Customer.setCurrentCustomerID("");
                });
    }

}

