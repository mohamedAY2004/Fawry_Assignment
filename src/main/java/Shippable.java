public class Shippable implements Trait {
    //Weight in Gram
    private final double weightInGram;

    public Shippable(double weightInGram) {
        this.weightInGram = weightInGram;
    }

    public double getWeightInGram() {
        return weightInGram;
    }

    @Override
    public Class<? extends Trait> getType() {
        return Shippable.class;
    }
}
