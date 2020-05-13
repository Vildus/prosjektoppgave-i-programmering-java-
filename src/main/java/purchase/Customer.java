package purchase;


public class Customer {
    private static String currentCustomerID;

    public static void setCurrentCustomerID(String customerID) {
        if (customerID.matches("[a-z,A-Z,0-9]{5,15}")) {
            currentCustomerID = customerID;
        } else {
            throw new IllegalCustomerIDException();
        }
    }

    public static String getCurrentCustomerID() {
        if (currentCustomerID == null) {
            // ideally this should not be handled by a runtime exception
            // and rather some other Custom exception, but for sake of simplicity ...
            throw new RuntimeException("current customer number is not set");
        }
        return currentCustomerID;
    }
}