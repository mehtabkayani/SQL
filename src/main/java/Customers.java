public class Customers {

    private int CustomerId;
    private String FirstName;
    private String LastName;



    public Customers(int customerId, String firstName, String lastName) {
        CustomerId = customerId;
        FirstName = firstName;
        LastName = lastName;


    }

    public int getCustomerId() {
        return CustomerId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
