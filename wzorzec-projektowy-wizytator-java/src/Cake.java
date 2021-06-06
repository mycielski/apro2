public class Cake implements Dessert {

    private final int price;

    public Cake(int cost) {
        this.price = cost;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int accept(SweetsBoughtVisitor visitor) {
        return visitor.visit(this);
    }

}