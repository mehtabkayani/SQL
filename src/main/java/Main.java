
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
         Db db = new Db();
         //Get customer
            System.out.println("Enter Customer ID: ");
            int customerId = scanner.nextInt();
            if(customerId==0){
                Customers customer = db.getRandomCustomer();
                customerId = customer.getCustomerId();
                db.getCustomerById(customerId);

            }else {

                db.getCustomerById(customerId);
            }
        List<Genre> mostPopularGenre = db.getGenre(customerId);
        for (Genre genre : mostPopularGenre) {
            System.out.println(genre);

        }

    }
}
