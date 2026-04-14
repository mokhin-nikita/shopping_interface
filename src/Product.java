import java.util.*;

public abstract class Product {

    private String name;
    private double prize;
    private int quantity;

    public Product(String name, double prize){
        this.name = name;
        this.prize = prize;
        quantity = 1;
    }

    public final void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public final int getQuantity() {
        return quantity;
    }

    public final double getPrize() {
        return prize;
    }

    public final double cost() {
        return prize * quantity;
    }
    public abstract String info();

    @Override
    public final String toString() {
        return String.format("%s - %s: %.0f * %d = %.0f", name, info(), prize, quantity, cost());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(name, prize, quantity);
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return Double.compare(prize, product.prize) == 0 && Objects.equals(name, product.name);
    }
}