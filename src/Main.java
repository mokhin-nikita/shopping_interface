import java.util.*;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Nikita", "Mokhin");
        Bank b = new Bank();
        int command;
        Scanner scanner = new Scanner(System.in);
        do {
            command = Integer.parseInt(scanner.nextLine());
            switch (command) {
                case 0:
                    break;
                case 1:
                    System.out.printf("Баланс: %.0f₽%n",person.checkBalance());
                    break;
                case 2:
                    String name = scanner.nextLine();
                    double prize = Double.parseDouble(scanner.nextLine());
                    String type = scanner.nextLine();
                    person.addProduct(name, prize, type);
                    break;
                case 3:
                    String name1 = scanner.nextLine();
                    double prize1 = Double.parseDouble(scanner.nextLine());
                    String type1 = scanner.nextLine();
                    person.deleteProduct(name1, prize1, type1);
                    break;
                case 4:
                    person.cancel();
                    break;
                case 5:
                    person.pay();
                    break;
                case 6:
                    System.out.println(person.getFinanceStatus());
                    break;
                case 7:
                    System.out.println(person.getShop());
                    break;
                case 8:
                    String field = scanner.nextLine();
                    System.out.println(person.getShop().showSearched(field));
                    break;
                case 9:
                    var products = new Product[person.getShop().getProducts().size()];
                    Arrayable<Product> arrayable = List::toArray;
                    arrayable.toArray(person.getShop().getProducts(), products);
                    person.getShop().getProducts().toArray(products);
                    Arrays.sort(products, Product::compareName);
                    Arrays.stream(products).toList().forEach(System.out::println);
                    break;
                case 10:
                    double am = Double.parseDouble(scanner.nextLine());
                    person.deposit(am);
                    break;
                case 11:
                    person.printActions();
                    break;
                case 12:
                    double am1 = Double.parseDouble(scanner.nextLine());
                    person.transfer(am1, b, false);
                    break;
                case 13:
                    double am2 = Double.parseDouble(scanner.nextLine());
                    person.transfer(am2, b, true);
                    break;
                default:
                    System.out.println("\033[31mWrong Command\033[0m");
            }
        }while (command != 0);
    }
}