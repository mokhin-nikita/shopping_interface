public class Food extends Product {
    public Food(String name, double prize) {
        super(name, prize);
    }

    @Override
    public String info() {
        return "Food";
    }
}
