import java.util.*;

public final class Shop implements Payable {
    private int id;
    private List<Product> products;
    private Status status;
    private FinanceStatus financeStatus;
    public Shop() {
        products = new ArrayList<>();
        status = Status.NEW;
        financeStatus = FinanceStatus.NONE;
        id = ++(IdTool.id);
    }

    public FinanceStatus getFinanceStatus() {
        return financeStatus;
    }

    @Override
    public double getFinalPrize() {
        double cost = 0;
        for(var p : products) {
            cost += p.cost();
        }
        return cost;
    }

    @Override
    public void pay() {
        if(isPaid() || products.isEmpty()) throw new IllegalArgumentException("No");
        status = Status.PAID;
        financeStatus = FinanceStatus.TOTAL;
    }
    public void cancel() {
        if(isPaid()) throw new IllegalArgumentException();
        status = Status.CANCELED;
    }
    @Override
    public boolean isPaid() {
        return status == Status.PAID;
    }
    public void addProduct(Product product) {
        for(var p : products) {
            if(product.equals(p)){
                p.setQuantity(p.getQuantity() + 1);
                financeStatus = FinanceStatus.EXPENSE;
                return;
            }
        }
        products.add(product);
        financeStatus = FinanceStatus.EXPENSE;
    }
    public void deleteProduct(Product product){
        for(var p  : products) {
            if(product.equals(p)){
                if(p.getQuantity() != 1) p.setQuantity(p.getQuantity() - 1);
                else products.remove(p);

                financeStatus = FinanceStatus.INCOME;
                return;
            }
        }
    }

    @Override
    public String toString() {
        String[] shop_string = new String[products.size()];
        for(int i=0; i< products.size(); i++){
            shop_string[i] = products.get(i).toString();
        }

        return !products.isEmpty()
                ? String.format("#%d - products:%n%s", id, String.join("\n", shop_string))
                : String.format("#%d - no products", id);
    }
}