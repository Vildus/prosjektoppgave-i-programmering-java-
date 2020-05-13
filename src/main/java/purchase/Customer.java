package purchase;


import purchase.exceptions.IllegalCustomerIDException;

public class Customer {

    private static String currentCustomerID;

    public static void setCurrentCustomerID(String customerID) {
        if (customerID.matches("[a-z,A-Z,0-9]{5,15}")) {
            currentCustomerID = customerID;
        } else {
            throw new IllegalCustomerIDException("You must enter an ID that contains between 5-15 characters or digits");
        }
    }

    public static String getCurrentCustomerID() {
        if (currentCustomerID == null) {
            // ideally this should not be handled by a runtime exception
            // and rather some other Custom exception
            throw new RuntimeException("current customer number is not set");
        }
        return currentCustomerID;
    }
}