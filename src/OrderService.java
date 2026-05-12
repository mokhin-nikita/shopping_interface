public final class OrderService {
    private PaymentStrategy paymentStrategy;
    public OrderService(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }
    public void checkout(double amount) {
        paymentStrategy.pay(amount);
    }
}
