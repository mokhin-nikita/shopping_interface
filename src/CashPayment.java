public final class CashPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.printf("Оплата наличными: %.0f₽%n", amount);
    }
}
