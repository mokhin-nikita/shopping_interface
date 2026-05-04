import java.util.function.Predicate;

public class Food extends Product implements Eatable {
    public Food(String name, double prize) {
        super(name, prize);
    }

    @Override
    public String info() {
        return "Food";
    }

    @Override
    public void eat() {
        System.out.println("Eating");
    }
}
