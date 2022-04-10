package BL;

import Data_Layer.Customer;
import Data_Layer.Order;
import Data_Layer.Product;
import Data_Layer.ProductCategory;

import java.util.List;

public interface IBL {
    Product getProductById(long productId);
    Order getOrderById(long orderId);
    Customer getCustomerById(long customerId);

    List<Product> getProducts(ProductCategory cat, double price);
    List<Customer> popularCustomers();
    List<Order>  getCustomerOrders(long customerId);
    long numberOfProductInOrder(long orderId);
    List<Product> getPopularOrderedProduct(int orderedtimes);
    List<Product> getOrderProducts(long orderId);
    List<Customer> getCustomersWhoOrderedProduct(long productId);
    Product getMaxOrderedProduct();
    double sumOfOrder(long orderID);
    List<Order> getExpensiveOrders(double price);
    List<Customer> ThreeTierCustomerWithMaxOrders();

}
