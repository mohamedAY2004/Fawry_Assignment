import java.util.ArrayList;
import java.util.List;

public class ProductBuilder {
    private String name;
    private double price;
    private int stock;
    private List<Trait> traits = new ArrayList<>();

    public ProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public ProductBuilder setStock(int stock) {
        this.stock = stock;
        return this;
    }

    public ProductBuilder addTrait(Trait trait) {
        Trait redunduntTrait=null;
        for (Trait t:traits){
            if(t.getClass()==trait.getClass())redunduntTrait=t;
        }
        traits.remove(redunduntTrait);
        traits.add(trait);
        return this;
    }

    public Product build() throws IllegalArgumentException {
        // Validate name
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        
        if (price <=0) {
            throw new IllegalArgumentException("Product price cannot be negative or zero");
        }
        
        // Validate stock
        if (stock <=0) {
            throw new IllegalArgumentException("Product stock cannot be negative or zero");
        }
        validateTraits();
        
        Product product = new Product(name, price, stock);
        for (Trait trait : traits) {
            product.addTrait(trait);
        }
        
        // Clear Builder for reuse
        traits = new ArrayList<>();
        name=null;
        price=0;
        stock=0;
        return product;
    }
    
    private void validateTraits() throws IllegalArgumentException {
        for (Trait trait : traits) {
            if (trait == null) {
                throw new IllegalArgumentException("Trait cannot be null");
            }
            if (trait instanceof Expirable) {
                Expirable expirable = (Expirable) trait;
                if (expirable.getExpiryDate() == null) {
                    throw new IllegalArgumentException("Expirable trait must have a valid expiry date");
                }
            }
            if (trait instanceof Shippable) {
                Shippable shippable = (Shippable) trait;
                if (shippable.getWeightInGram() <=0) {
                    throw new IllegalArgumentException("Shippable trait weight cannot be negative or zero");
                }
            }
        }
    }
}
