package BL;

import Data_Layer.Customer;
import Data_Layer.Order;
import Data_Layer.Product;
import BL.*;
import Data_Layer.ProductCategory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Data_Layer.DataSource.*;
import static java.util.Collections.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.*;

public class BL implements IBL {
    @Override
    public Product getProductById(long productId) {
        Stream<Product> stream = allProducts.stream();
        return stream.filter(e-> e.getProductId()==productId).collect(Collectors.toList()).get(0);
    }

    @Override
    public Order getOrderById(long orderId) {
        Stream<Order> stream = allOrders.stream();
        return stream.filter(e-> e.getOrderId()==orderId).collect(Collectors.toList()).get(0);
    }

    @Override
    public Customer getCustomerById(long customerId) {
        Stream<Customer> stream = allCustomers.stream();
        return stream.filter(e-> e.getId()==customerId).collect(Collectors.toList()).get(0);
    }


    @Override
    public List<Product> getProducts(ProductCategory cat, double price) {
        Stream<Product> stream = allProducts.stream();
        return stream.filter(e -> e.getCategory()==cat&&e.getPrice()<=price).
                sorted((e,i)-> Long.valueOf(e.getProductId()).compareTo(i.getProductId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> popularCustomers() {
        Stream<Customer> stream = allCustomers.stream();
        Function<Long ,List<Order>> myOrders = (e)-> allOrders.stream().filter(id -> id.getCustomrId()==e).collect(Collectors.toList());
        return stream.filter(e -> e.getTier()==3&& myOrders.apply(e.getId()).size()>=10).
                sorted((e,i)-> Long.valueOf(e.getId()).compareTo(i.getId()))
                .collect(Collectors.toList());

    }

    @Override
    public List<Order> getCustomerOrders(long customerId) {
        Function<Long ,List<Order>> myOrders = (e)-> allOrders.stream().filter(id -> id.getCustomrId()==e).collect(Collectors.toList());
        return myOrders.apply(customerId).stream().sorted((e,i)-> Long.valueOf(e.getOrderId()).compareTo(i.getOrderId()))
                .collect(Collectors.toList());
    }

    @Override
    public long numberOfProductInOrder(long orderId) {
        //To do
        return 0;
    }

    @Override
    public List<Product> getPopularOrderedProduct(int orderedtimes) {
        //To do
        return null;
    }

    @Override
    public List<Product> getOrderProducts(long orderId)
    {
        //To do
        return null;
    }

    @Override
    public List<Customer> getCustomersWhoOrderedProduct(long productId) {
        //To do
        return null;
    }

    @Override
    public Product getMaxOrderedProduct() {
        //To do
        return null;

    }
    @Override
    public double sumOfOrder(long orderID) {
        //To do
        return 0;
    }

    @Override
    public List<Order> getExpensiveOrders(double price) {
        //To do
        return null;
    }

    @Override
    public List<Customer> ThreeTierCustomerWithMaxOrders() {
        //To do
        return null;

    }

}
