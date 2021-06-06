public class Cookie implements Dessert {

    private final int pricePerKg;
    private final int weight;

    public Cookie(int priceKg, int wt) {
        this.pricePerKg = priceKg;
        this.weight = wt;
    }

    public int getPricePerKg() {
        return pricePerKg;
    }


    public int getWeight() {
        return weight;
    }

    @Override
    public int accept(SweetsBoughtVisitor visitor) {
        return visitor.visit(this);
    }

}