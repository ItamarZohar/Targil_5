package BL;

import Data_Layer.*;
import BL.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
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
        Stream<OrderProduct> stream = allOrderProducts.stream();
        return stream.filter(e -> e.getOrderId()==orderId).collect(Collectors.toList()).size();
    }

    @Override
    public List<Product> getPopularOrderedProduct(int orderedtimes) {
        Stream<Product> stream = allProducts.stream();
        Function<Long,Integer> myOrder = e -> allOrderProducts.stream().filter(id -> id.getProductId()==e).collect(Collectors.toList()).size();
        return stream.filter(e-> (myOrder.apply(e.getProductId()))>=orderedtimes).collect(Collectors.toList());
    }

    @Override
    public List<Product> getOrderProducts(long orderId)
    {
       Stream<OrderProduct> myOrderP = allOrderProducts.stream().filter(i -> i.getOrderId()==orderId);
        Stream<Product> myPstream = allProducts.stream();
        return myPstream.filter(e -> myOrderP.anyMatch(i -> i.getProductId()==e.getProductId())).
                sorted((e,i)-> Long.valueOf(e.getProductId()).compareTo(i.getProductId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> getCustomersWhoOrderedProduct(long productId) {
        //To do
        return null;
    }

    @Override
    public Product getMaxOrderedProduct() {  //////////*********************************************
       Stream<Product> stream = allProducts.stream();
        Function<Long ,Integer> myOrdersP = (e)-> allOrderProducts.stream().filter(id -> id.getProductId()==e).collect(Collectors.toList()).size();
        return stream.max((Comparator<? super Product>) myOrdersP).orElse(null);
    }
    @Override
    public double sumOfOrder(long orderID) {
        //To do
        return 0;
    }

    @Override
    public List<Order> getExpensiveOrders(double price) {
        Stream<Order> stream = allOrders.stream();




        return null;
    }

    @Override
    public List<Customer> ThreeTierCustomerWithMaxOrders() {
        Function<Long ,List<Order>> myOrders = (e)-> allOrders.stream().filter(id -> id.getCustomrId()==e).collect(Collectors.toList());
        // num of order for cumtmaor


        return null;

    }

}
