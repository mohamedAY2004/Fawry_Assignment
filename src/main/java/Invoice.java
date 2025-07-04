import java.util.ArrayList;

public class Invoice {
    ArrayList<CartItem>cartItems;
    Customer customer;
    boolean isCheckedOut=false;
    public Invoice(Customer c) throws IllegalArgumentException{
        if(c==null) throw new IllegalArgumentException("Customer cannot be null");
        customer=c;
        cartItems=new ArrayList<>();
    }
    public void addItem(Product p,int q){
        try{
            CartItem c=new CartItem(p,q);
            cartItems.add(c);
        }catch (IllegalArgumentException e){
            System.out.printf("Couldn't add %s because of %s\n",p.getName(),e.getMessage());
        }
    }
    public void removeItem(Product p){
        for(CartItem i:cartItems){
            if(i.getProductName().equals(p.getName())) {
                cartItems.remove(i);
                return;
            }
        }
    }
    public void checkOut(){
        if(isCheckedOut){
            System.out.println("Invoice already checked out");
            return;
        }
        if(cartItems.isEmpty()){
            System.out.println("Cart is empty!!");
            return;
        }
        double orderSubtotal=0,totalWeight=0;
        ArrayList<CartItem>shippableProducts=new ArrayList<>();
        for(CartItem c: cartItems){
            orderSubtotal+=c.getPrice();
            if(c.getWeight()!=0){
                shippableProducts.add(c);
                totalWeight+=c.getWeight();
            }
            if(c.isExpired()){
                System.out.printf("Product %s Expired remove from cart to checkout\n",c.getProductName());
                return;
            }
            if(c.getStock()<c.getQuantity())
            {
                System.out.printf("Not enough quantity of %s Only %d in stock",c.getProductName(),c.getStock());
                return;
            }
            if(c.getQuantity()<=0){
                System.out.printf("quantity cannot be zero or negative of %s ",c.getProductName());
                return;
            }
        }

        // shipment is 100 pounds per 1000g
        double total=orderSubtotal+totalWeight*0.1;
        if(total>customer.getBalance()){
            System.out.println("Insufficient Customer Balance");
            return;
        }
        if(!shippableProducts.isEmpty()){
            ShippingService.ship(new ArrayList<>(shippableProducts));
        }
        System.out.println("** Checkout receipt **");
        for(CartItem c:cartItems) {
            String s = String.format("%dx  %S      %.2f", c.getQuantity(), c.getProductName(), c.getPrice());
            System.out.println(s);
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal        %.2f\n",orderSubtotal);
        if(totalWeight!=0)
            System.out.printf("Shipping        %.2f\n",totalWeight*0.1);
        System.out.printf("Total        %.2f\n",total);
        for(CartItem c: cartItems){
            c.setAsSold();
        }
        customer.setBalance(customer.getBalance()-total);
        isCheckedOut=true;

    }
}
