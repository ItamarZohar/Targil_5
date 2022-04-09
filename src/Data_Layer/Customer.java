package Data_Layer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;

public class Customer {
 private long id;
 private String name;
 private int tier;

 public Customer(String customerInfo)
 {
  List<String> myData=stream(customerInfo.split(" ")).collect(Collectors.toList());
   id= Integer.valueOf(myData.get(1));
   name = myData.get(3);
   tier =Integer.valueOf(myData.get(5));

 }

 public Customer(long Cid,String Cname, int Ctier)
 {
  setId(Cid);
  setName(Cname);
  setTier(Ctier);
 }

 public String toString()
 {
  return "customer: "+ getId() + " name: "+ getName() +" tier: "+ getTier()+"\n";
 }

 public long getId() {
  return id;
 }

 public void setId(long id) {
  this.id = id;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public int getTier() {
  return tier;
 }

 public void setTier(int tier) {
  this.tier = tier;
 }
}