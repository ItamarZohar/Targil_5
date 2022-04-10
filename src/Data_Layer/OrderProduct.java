package Data_Layer;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;

public class OrderProduct {
    private long orderId;
    private long productId;
    private int quantity;

    public OrderProduct(String orderInfo)
    {
        List<String> myData=stream(orderInfo.split(" ")).collect(Collectors.toList());
        orderId = Long.valueOf(myData.get(2));
        productId = Long.valueOf(myData.get(5));
        quantity = Integer.valueOf(myData.get(7));
    }

    public OrderProduct(long oId, long pId, int quantity)
    {
        setOrderId(oId);
        setProductId(pId);
        setQuantity(quantity);
    }

    public String toString()
    {
        return "ord id: "+ getOrderId() + " prod id: "+ getProductId() +" quantity: "+ getQuantity()+"\n";
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
