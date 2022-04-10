package Data_Layer;



import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class DataSource {

    public static List<Customer> allCustomers;
    public static List<Order> allOrders;
    public static List<Product> allProducts;
    public static List<OrderProduct> allOrderProducts;
    // Update this path according to your data files location
    public static String basePath = "C:\\Users\\itamar\\Desktop\\DataFiles\\";
    public static String customersPath = basePath +"customers.txt";
    public static String ordersPath = basePath +"orders.txt";
    public static String productsPath = basePath +"products.txt";
    public static String orderProductPath = basePath +"orderProduct.txt";

    static
    {
        try {
            allCustomers = readCustomersfromFile();
            allOrders = readOrdersfromFile();
            allProducts = readProductsfromFile();
            allOrderProducts = readOrderProductsfromFile();
        } catch (IOException e) { e.printStackTrace(); }
    }
    public static List<Customer> readCustomersfromFile() throws IOException {
        try (Stream<String> stream = Files.lines(Path.of(customersPath)))
        {
          stream.forEach(e -> allCustomers.add(new Customer(e.toString())));
        }catch (IOException E){E.printStackTrace();}

        return allCustomers;
    }

    public static List<Order> readOrdersfromFile() throws IOException {
        try (Stream<String> stream = Files.lines(Path.of(ordersPath)))
        {

            stream.forEach(e -> {
                try {
                    allOrders.add(new Order(e.toString()));
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            });

        }catch (IOException E){E.printStackTrace();}


        return allOrders;
    }

    public static List<Product> readProductsfromFile() throws IOException {
        //To Do
        try (Stream<String> stream = Files.lines(Path.of(productsPath)))
        {
            stream.forEach(e -> allProducts.add(new Product(e.toString())));
        }catch (IOException E){E.printStackTrace();}

        return allProducts;
    }

    public static List<OrderProduct> readOrderProductsfromFile() throws IOException {
        try (Stream<String> stream = Files.lines(Path.of(orderProductPath)))
        {
            stream.forEach(e -> allOrderProducts.add(new OrderProduct(e.toString())));
        }catch (IOException E){E.printStackTrace();}

        return allOrderProducts;
    }
}



