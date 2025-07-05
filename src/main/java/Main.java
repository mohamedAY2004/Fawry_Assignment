import java.time.LocalDate;

public class Main {

    public static void main(String[]args){
       //The ProductBuilder is Reusable and clear values after each successful build
        ProductBuilder builder=new ProductBuilder();

       try {
           Product cheese=builder
                   .setName("Cheese")
                   .setPrice(6)
                   .setStock(8)
                   .addTrait(new Expirable(LocalDate.of(2025,7,6)))
                   .addTrait(new Shippable(400))
                   .build();

           Product biscuit=builder
                   .setName("Biscuit")
                   .setPrice(110)
                   .setStock(83)
                   .addTrait(new Expirable(LocalDate.of(2024,7,4)))
                   .build();

           Product smartTV=builder
                   .setName("smartTV")
                   .setPrice(23500)
                   .setStock(1)
                   .addTrait(new Shippable(10705))
                   .build();

           Product Phone=builder
                   .setName("SmartPhone")
                   .setPrice(17350)
                   .setStock(9)
                   .addTrait(new Shippable(500))
                   .build();

           Product mobileScratch=builder
                   .setName("Mobile Scratch Card")
                   .setPrice(150)
                   .setStock(68)
                   .build();

           Customer c=new Customer("Mohamed Younes",50000);
           Invoice i=new Invoice(c);
           i.addItem(cheese,9);
           i.addItem(biscuit,5);
           i.addItem(smartTV,1);
           i.removeItem(cheese);
           i.addItem(cheese,6);
           i.addItem(Phone,1);
           i.addItem(mobileScratch,1);
           i.checkOut();

       } catch (IllegalArgumentException e) {
           System.err.println("Error creating products: " + e.getMessage());
       }
    }
}
