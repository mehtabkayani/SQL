import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Db {
    private static String URL = "jdbc:sqlite::resource:chinook.db";
    private static Connection conn = null;
    public static ArrayList<Customers> customers = new ArrayList<>();
    Customers customer;
    Genre genre;
    static List<Genre> genreList = new ArrayList<>();


    public Customers getRandomCustomer() {
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            //System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName from Customers ORDER BY RANDOM() LIMIT 1");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customer =
                        new Customers(
                                resultSet.getInt("CustomerId"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName")
                        );
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        } finally {
            try {
                // Close Connection
                conn.close();
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
        }
        return customer;
    }


    public List<Genre> getGenre(int id){
        List<Genre> genres = new ArrayList<>();
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            //System.out.println("Connection to SQLite has been established.");

            // Prepare Statement

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT genres.name, genres.GenreId, count(genres.GenreId) as most_popular from genres\n" +
                            "    INNER JOIN tracks t on genres.GenreId = t.GenreId\n" +
                            "    INNER JOIN invoice_items ii on t.TrackId = ii.TrackId\n" +
                            "    INNER JOIN invoices i on ii.InvoiceId = i.InvoiceId\n" +
                            "    INNER JOIN customers c on i.CustomerId = c.CustomerId\n" +
                            "    WHERE c.CustomerId = ? GROUP BY genres.name order by most_popular Desc");
            preparedStatement.setInt(1,id);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            int previous = 0;
            while (resultSet.next()) {
                int current = resultSet.getInt("most_popular");
                if (current >= previous) {
                    previous = current;

                    genres.add(new Genre(resultSet.getInt("GenreId"),resultSet.getString("Name")));
                }


            }
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        } finally {
            try {
                // Close Connection
                conn.close();
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return genres;
        }


    }

    public void mostPopularGenre(){

        for (Genre genre:genreList) {
            System.out.println(genre.getName());
        }
    }

    public void getCustomerById(int id){
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            //System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId,FirstName,LastName FROM customers where CustomerId=" + id);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customer =
                        new Customers(
                                resultSet.getInt("CustomerId"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName")
                        );
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        } finally {
            try {
                // Close Connection
                conn.close();
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
        }

            System.out.println(customer.getCustomerId() + " " + customer.getFirstName() + " " + customer.getLastName());

    }
}
