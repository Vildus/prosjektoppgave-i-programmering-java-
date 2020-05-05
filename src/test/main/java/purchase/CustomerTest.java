package purchase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testValidCustomerID() {
        Customer.setCurrentCustomerID("sluttbruker1");
        assertEquals("sluttbruker1", Customer.getCurrentCustomerID());
        Customer.setCurrentCustomerID("12345");
        assertEquals("12345", Customer.getCurrentCustomerID());
        Customer.setCurrentCustomerID("sluttbruker");
        assertEquals("sluttbruker", Customer.getCurrentCustomerID());
    }

    @Test
    void testInvalidCustomerID() {
        assertThrows(
                //assertThrows tar to arguments: 1.arg = den exception man vil ha. 2.argument = lamda(metoden)
                IllegalCustomerIDException.class,
                () -> {
                    Customer.setCurrentCustomerID("123");
                });
        assertThrows(
                //assertThrows tar to arguments: 1.arg = den exception man vil ha. 2.argument = lamda(metoden)
                IllegalCustomerIDException.class,
                () -> {
                    Customer.setCurrentCustomerID("abc");
                });
        assertThrows(
                //assertThrows tar to arguments: 1.arg = den exception man vil ha. 2.argument = lamda(metoden)
                IllegalCustomerIDException.class,
                () -> {
                    Customer.setCurrentCustomerID("1bc");
                });
        assertThrows(
                //assertThrows tar to arguments: 1.arg = den exception man vil ha. 2.argument = lamda(metoden)
                IllegalCustomerIDException.class,
                () -> {
                    Customer.setCurrentCustomerID(" ");
                });
        assertThrows(
                //assertThrows tar to arguments: 1.arg = den exception man vil ha. 2.argument = lamda(metoden)
                IllegalCustomerIDException.class,
                () -> {
                    Customer.setCurrentCustomerID("");
                });
    }

}

