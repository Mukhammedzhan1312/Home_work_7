import java.util.Scanner;
interface IPaymentStrategy {
    void pay(double amount);
}

class CreditCardPaymentStrategy implements IPaymentStrategy {
    public void pay(double amount) {
        System.out.println("Оплата " + amount + " рублей с использованием банковской карты.");
    }
}

class PayPalPaymentStrategy implements IPaymentStrategy {
    public void pay(double amount) {
        System.out.println("Оплата " + amount + " рублей через PayPal.");
    }
}

class CryptocurrencyPaymentStrategy implements IPaymentStrategy {
    public void pay(double amount) {
        System.out.println("Оплата " + amount + " рублей с использованием криптовалюты.");
    }
}

class PaymentContext {
    private IPaymentStrategy paymentStrategy;

    public void setPaymentStrategy(IPaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Стратегия оплаты не установлена.");
        }
        paymentStrategy.pay(amount);
    }
}


public class Main {
    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите стратегию оплаты:");
            System.out.println("1 - Оплата картой");
            System.out.println("2 - Оплата через PayPal");
            System.out.println("3 - Оплата криптовалютой");
            System.out.println("0 - Выход");
            int choice = scanner.nextInt();

            if (choice == 0) {
                break;
            }

            switch (choice) {
                case 1:
                    paymentContext.setPaymentStrategy(new CreditCardPaymentStrategy());
                    break;
                case 2:
                    paymentContext.setPaymentStrategy(new PayPalPaymentStrategy());
                    break;
                case 3:
                    paymentContext.setPaymentStrategy(new CryptocurrencyPaymentStrategy());
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
                    continue;
            }

            System.out.print("Введите сумму оплаты: ");
            double amount = scanner.nextDouble();
            paymentContext.executePayment(amount);
        }

        scanner.close();
    }
}
