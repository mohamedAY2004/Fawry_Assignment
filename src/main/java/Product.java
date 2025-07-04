import java.util.ArrayList;
import java.util.List;

public class Product {
    private String  name;
    private double price;
    private int stock;
    private List<Trait> traits;

    public Product(String name, double price, int stock) {
        this.name=name;
        this.price=price;
        this.stock=stock;
        this.traits = new ArrayList<Trait>();
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", traits=" + traits +
                '}';
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }
    public void addTrait(Trait trait){
        traits.add(trait);
    }
    public  <T extends Trait> T getTrait(Class<T> traitClass) {
        for (Trait trait:traits){
            if(traitClass.isInstance(trait))return (T) trait;
        }

        return null;
    }
}
