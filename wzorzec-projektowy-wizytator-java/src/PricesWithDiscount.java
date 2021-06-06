public class PricesWithDiscount implements SweetsBoughtVisitor {

    @Override
    public int visit(Cake cake) {
        // 10 PLN off books more expensive than 50
        return (cake.getPrice() > 50) ? cake.getPrice()-10 : cake.getPrice();
    }

    @Override
    public int visit(Cookie cookie) {
        // cost of fruit is equal to its price per unit of weight times its weight
        return cookie.getPricePerKg() * cookie.getWeight();
    }

}
