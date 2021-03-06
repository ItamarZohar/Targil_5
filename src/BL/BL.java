package BL;

import Data_Layer.*;
import BL.*;

import java.util.*;
import java.util.function.BiFunction;
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
    // Stream<OrderProduct> myOP = allOrderProducts.stream();
    // Predicate<Product> P = i-> myOP.filter(o->o.getOrderId()==orderId).anyMatch(j-> j.getProductId()==i.getProductId());
     Stream<Product> myP = allProducts.stream();
     //return myP.filter(P).collect(Collectors.toList());

     return myP.filter(i-> allOrderProducts.stream().filter(o->o.getOrderId()==orderId).anyMatch(j-> j.getProductId()==i.getProductId())).collect(Collectors.toList());
    }

    @Override
    public List<Customer> getCustomersWhoOrderedProduct(long productId) {
        Stream<Customer> C = allCustomers.stream();

       return C.filter(i-> getCustomerOrders(i.getId()).stream().
                filter(j-> getOrderProducts(j.getOrderId()).stream().anyMatch(k->k.getProductId()==productId))
                .anyMatch(e->e!=null)).collect(Collectors.toList());


        // ???????? ???? ?????????????? ???? ??????????
        // ???? ?????????? ???????? ????????????
        // ???? ?????????????? ?????? ???????? ?????????? ???? ??????????


    }

    @Override
    public Product getMaxOrderedProduct() {
       Stream<Product> stream = allProducts.stream();
        Function<Double ,Integer> myOrdersP = (e)-> allOrderProducts.stream().filter(id -> id.getProductId()==e).collect(Collectors.toList()).size();
        return stream.max((x,y)-> myOrdersP.apply(x.getPrice())-myOrdersP.apply(y.getPrice())).orElse(null);
    }



    @Override
    public double sumOfOrder(long orderID) {
        Stream<Order> stream = allOrders.stream();
        return getOrderProducts(orderID).stream().map(x -> x.getPrice()).reduce((x, y) -> x + y).stream().collect(Collectors.toList()).get(0);
    }

    @Override
    public List<Order> getExpensiveOrders(double price) {
        Stream<Order> stream = allOrders.stream();

      //  stream.filter(i-> getOrderProducts(i.getOrderId()).stream().map(j->j.getPrice())
       //         .reduce((x,y)->x+y).stream().collect(Collectors.toList()).get(0)>0;
       Predicate<Long> Pi = i -> sumOfOrder(i)>price;
        return stream.filter(i -> Pi.test(i.getOrderId())).collect(Collectors.toList());
        //???????? ???? ??????????????
        //???????? ???? ?????????? ???????? ???? ???? ?????????????? ??????
        // ?????????? ???? ???????? ?????????????? ?????? ???????? ?????????????? ???????? ?????? ????
    }

    @Override
    public List<Customer> ThreeTierCustomerWithMaxOrders() {
        Function<Long ,Integer> myOrders = (e)-> allOrders.stream().filter(id -> id.getCustomrId()==e).collect(Collectors.toList()).size();
        //num of order for the customer
        Stream<Customer> stream = allCustomers.stream();
        return stream.filter(e->e.getTier()==3).sorted((x,y)-> myOrders.apply(x.getId())-myOrders.apply(y.getId()))
               /* sorted(Collections.reverseOrder())*/.limit(3).collect(Collectors.toList());
    }

}
