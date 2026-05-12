public final class CardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.printf("Оплата банковской картой: %.0f₽%n", amount);
    }
}
