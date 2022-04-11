package Data_Layer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;

public class Product
{
    private long ProductId;
    private String name;
    private ProductCategory category;
    private double price;

    public Product(String orderInfo)
    {
        List<String> myData=stream(orderInfo.split(" ")).collect(Collectors.toList());
        ProductId = Long.parseLong(myData.get((1)));
        name = myData.get(2);
        Stream<ProductCategory> stream = stream(ProductCategory.values());
        category= ProductCategory.valueOf(myData.get(4));
        price = Double.parseDouble(myData.get(6));
    }


    public Product(long PId, String Pname, ProductCategory Pcategory, double Pprice)
    {
        setProductId(PId);
        setName(Pname);
        setCategory(Pcategory);
        setPrice(Pprice);

    }


    public String toString()
    {
        return "Product: "+ getProductId() + " "+ getName() +" category: "+ getCategory() + " price: "+ getPrice()+"\n";
    }

    public long getProductId() {
        return ProductId;
    }

    public void setProductId(long productId) {
        ProductId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}