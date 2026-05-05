public final class Bank {
    double balance = 0;

    public void addBalance(double amount) {
        balance += amount;
    }
    public boolean removeBalance(double amount) {
        if(balance < amount) return false;
        balance -= amount;
        return true;
    }
}
