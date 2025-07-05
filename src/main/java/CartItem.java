public class CartItem implements ShippableItem {
    private Product item;
    private int quantity;
    private boolean isSold;
    public CartItem(Product item, int quantity)throws IllegalArgumentException {
        this.item = item;
        int stock=item.getStock();
        Expirable e=item.getTrait(Expirable.class);
        if(quantity<=0){
            throw new IllegalArgumentException("Quantity cannot be zero or negative");
        }
        if(e!=null&&e.isExpired()){
            throw new IllegalArgumentException("Item Expired Cannot add to cart");
        }
        if(stock<quantity){
            throw new IllegalArgumentException(String.format("Not enough quantity Only %d in stock",item.getStock()) );
        }else{
            this.quantity = quantity;
        }
    }
    public boolean isExpired(){
        Expirable e= item.getTrait(Expirable.class);
        if(e!=null&&e.isExpired())return true;
        return false;
    }
    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){if(!isSold) this.quantity=quantity;}
    public int getStock(){return item.getStock();}
    public double getPrice(){
        return item.getPrice()*quantity;
    }
    public Product getProduct(){return item;}
    public String getProductName(){
        return item.getName();
    }
    public double getWeight() {
        Shippable s=item.getTrait(Shippable.class);
        if(s==null)return 0;
        else return s.getWeightInGram()*quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "item=" + item +
                ", quantity=" + quantity +
                ", isSold=" + isSold +
                '}';
    }

    public void setAsSold(){
        if(isSold)return;
        isSold=true;
        item.setStock(item.getStock()-quantity);
        System.out.printf("Sold %d of %s remaining in stock: %d\n",quantity,item.getName(),item.getStock());
    }

    @Override
    public String getName() {
        return item.getName();
    }
}
