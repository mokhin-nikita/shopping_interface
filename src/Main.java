import java.util.*;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Nikita", "Mokhin");
        Bank b = new Bank();
        int command;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Введите номер команды");
            System.out.println("1 - баланс");
            System.out.println("2 - добавить продукт");
            System.out.println("3 - удалить продукт");
            System.out.println("4 - отмена");
            System.out.println("5 - оплатить");
            System.out.println("6 - статус");
            System.out.println("7 - список продуктов");
            System.out.println("8 - поиск по имени");
            System.out.println("9 - сортировка по имени");
            System.out.println("10 - баланс");
            System.out.println("11 - история");
            System.out.println("12 - перевод в банк");
            System.out.println("13 - перевод с банка");
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
                    boolean bonusActive = false;
                    var finalPrize = person.getShop().getFinalPrize();
                    if(finalPrize == 0) {
                        break;
                    }
                    String payType;
                    do {
                        System.out.println("Введите тип оплаты:");
                        System.out.println("card - банковская карта");
                        System.out.println("money - наличные");
                        System.out.println("при некорректном типе - повтор");
                        payType = scanner.nextLine();
                        switch (payType) {
                            case "money":
                            case "card":
                                break;
                            case "bonus":
                                int precent = Integer.parseInt(scanner.nextLine());
                                if(!bonusActive) {
                                    try{
                                        BonusManager manager = BonusManager.createBonus(finalPrize);
                                        finalPrize = manager.checkBonus(precent);
                                        bonusActive = true;
                                    } catch (Exception e) {
                                        break;
                                    }
                                }
                                break;
                            default:
                                System.out.println("\033[31mWrong Type\033[0m");
                        }
                    }while (!payType.equals("money") && !payType.equals("card"));

                    OrderService service = new OrderService(
                            PaymentFromType(
                                    moneyTypeFromString(payType)
                            )
                    );
                    person.pay(service, finalPrize);
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
    private static MoneyType moneyTypeFromString(String type) {
        return switch(type) {
            case "card" -> MoneyType.CARD;
            case "money" -> MoneyType.CASH;
            default -> throw new IllegalArgumentException("Wrong");
        };
    }
    private static PaymentStrategy PaymentFromType(MoneyType type) {
        return switch (type) {
            case CARD -> new CardPayment();
            case CASH -> new CashPayment();
        };
    }
}