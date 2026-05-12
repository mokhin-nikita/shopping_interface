public final class BonusManager {
    private double balance;
    private static BonusManager bonusManager;
    private BonusManager(double balance) {
        this.balance = balance;
    }
    public static BonusManager createBonus(double balance) {
        if(bonusManager == null) bonusManager = new BonusManager(balance);
        return bonusManager;
    }

    public double checkBonus(int precent) {
        if(precent < 0 || precent > 100) throw new IllegalArgumentException("Wrong Precent");
        return balance * (precent / (double)100);
    }
}