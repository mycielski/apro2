public class Main {

    public static void main(String[] args) {
        Dessert[] items = new Dessert[]{new Cake(20), new Cake(100), new Cookie(10, 2), new Cookie(5, 5)};
        SweetsBoughtVisitor visitor = new PricesWithDiscount();
        int sum = 0;
        for (Dessert item : items) {
            sum = sum + item.accept(visitor);
        }
        System.out.println("Total price with discount = " + sum + " PLN");
    }
}