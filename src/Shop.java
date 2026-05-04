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

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void pay() {
        if(isPaid() || products.isEmpty()) throw new IllegalArgumentException("No");
        status = Status.PAID;
        financeStatus = FinanceStatus.TOTAL;
    }
    public void cancel() {
        if(isPaid()) throw new IllegalArgumentException("No");
        status = Status.CANCELED;
    }
    @Override
    public boolean isPaid() {
        return status == Status.PAID;
    }
    public void addProduct(Product product) {
        var found = products.stream().anyMatch(p -> p.equals(product));
        if(!found) products.add(product);
        financeStatus = FinanceStatus.EXPENSE;
    }
    public void deleteProduct(Product product){
        var found = products.stream().anyMatch(p -> p.equals(product));
        if(found) {
            var foundProduct = products.stream().filter(p -> p.equals(product)).findFirst().orElse(null);
            if(Objects.requireNonNull(foundProduct).getQuantity() != 1) foundProduct.setQuantity(foundProduct.getQuantity() - 1);
            else products.remove(foundProduct);
            financeStatus = FinanceStatus.INCOME;

        }
    }

    @Override
    public String toString() {

        var shop_string = products.stream().map(Product::toString).toList();
        return !products.isEmpty()
                ? String.format("#%d - products:%n%s", id, String.join("\n", shop_string))
                : String.format("#%d - no products", id);
    }

    public String showSearched(String field) {
        var result = products.stream().filter(p -> p.getName().startsWith(field)).toList();
        if(result.isEmpty()) return "Not found";
        var result_string = result.stream().map(Product::toString).toList();
        return String.join("\n", result_string);
    }
}