import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDAO {

    public void displayAllProducts() {
        try (Connection connection = DBConfig.getConnection()) {
            if (connection != null) {
                try (Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery("SELECT * FROM Product")) {

                    System.out.printf("%-5s %-20s %-15s %-15s\n", "ID", "Name", "Price Per Unit", "Active For Sell");

                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        double pricePerUnit = resultSet.getDouble("price_per_unit");
                        boolean activeForSell = resultSet.getBoolean("active_for_sell");

                        System.out.printf("%-5d %-20s %-15.2f %-15b\n", id, name, pricePerUnit, activeForSell);
                    }
                } catch (SQLException e) {
                    System.err.println("Failed to execute query.");
                    e.printStackTrace();
                }
            } else {
                System.err.println("Failed to establish connection.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to establish connection.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        productDAO.displayAllProducts();
    }
}
