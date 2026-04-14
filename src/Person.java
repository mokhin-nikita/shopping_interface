public final class Person implements Financable {
    private String name, surname;
    private Shop shop;
    private double balance;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        balance = 1000;
        shop = new Shop();
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
        shop.pay();
    }
    public void addProduct(String name, double prize, String type) {
        Product product = getProduct(name, prize, type);
        if(HasEnoughMoney()){
            shop.addProduct(product);
            if(shop.getFinanceStatus() == FinanceStatus.EXPENSE) balance -= product.getPrize();
        }
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
        if(shop.getFinanceStatus() == FinanceStatus.INCOME) balance += product.cost();
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
}