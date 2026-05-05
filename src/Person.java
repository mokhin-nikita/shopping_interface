import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public final class Person implements Financable {
    private String name, surname;
    private Shop shop;
    private double balance;
    private ArrayList<String> actions;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        balance = 1000;
        shop = new Shop();
        actions = new ArrayList<>();
    }
    public void transfer(double amount, Bank bank, boolean toEnd){
        if(toEnd) {
            boolean result = bank.removeBalance(amount);
            if(result) balance += amount;
            return;
        }
        balance -= amount;
        bank.addBalance(amount);
    }
    @Override
    public double checkBalance() {
        return balance;
    }

    @Override
    public boolean HasEnoughMoney() {
        return balance >= shop.getFinalPrize();
    }

    @Override
    public String getFinanceStatus() {
        return switch(shop.getFinanceStatus()){
            case EXPENSE -> "Расход";
            case INCOME -> "Доход";
            case TOTAL -> "Итого";
            default -> "Ничего";
        };
    }
    public void pay() {
        if(HasEnoughMoney()) {
            shop.pay();
            balance -= getShop().getFinalPrize();
            actions.add(String.format("%s -%.0f₽ - (%s)", getFinanceStatus(), getShop().getFinalPrize(), DateStringFormatter.Format(LocalDateTime.now())));
        }else{
            System.out.println("\033[31mНедостаточно средств\033[0m");
        }
    }
    public void addProduct(String name, double prize, String type) {
        Product product = getProduct(name, prize, type);
        shop.addProduct(product);
        actions.add(String.format("%s -%.0f₽ - (%s)", getFinanceStatus(), product.getPrize(), DateStringFormatter.Format(LocalDateTime.now())));
    }
    private Product getProduct(String name, double prize,String type) {
        return switch (type){
            case "Electronic" -> new Electronic(name, prize);
            case "Food" -> new Food(name, prize);
            default -> throw new IllegalArgumentException("Wrong Type");
        };
    }
    public void deleteProduct(String name, double prize, String type){
        Product product = getProduct(name, prize, type);
        shop.deleteProduct(product);
        actions.add(String.format("%s %+.0f₽ - (%s)", getFinanceStatus(), product.getPrize(), DateStringFormatter.Format(LocalDateTime.now())));
    }
    public void cancel() {
        shop.cancel();
    }

    @Override
    public String toString() {
        return String.format("%s %s", name, surname);
    }

    public Shop getShop() {
        return shop;
    }

    public void deposit(double amount) {
        if(amount < 0) throw new IllegalArgumentException("No");
        balance += amount;
        shop.setFinanceStatus(FinanceStatus.INCOME);
        actions.add(String.format("%s %+.0f₽ - (%s)", getFinanceStatus(), amount, DateStringFormatter.Format(LocalDateTime.now())));
    }

    public void printActions() {
        actions.forEach(System.out::println);
    }
}