import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
interface IObserver {
    void update(String currency, double newRate);
}

interface ISubject {
    void attach(IObserver observer);
    void detach(IObserver observer);
    void notifyObservers(String currency, double newRate);
}
class CurrencyExchange implements ISubject {
    private final List<IObserver> observers = new ArrayList<>();
    private final java.util.Map<String, Double> rates = new java.util.HashMap<>();

    public void attach(IObserver observer) {
        observers.add(observer);
    }

    public void detach(IObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String currency, double newRate) {
        for (IObserver observer : observers) {
            observer.update(currency, newRate);
        }
    }

    public void updateRate(String currency, double newRate) {
        rates.put(currency, newRate);
        notifyObservers(currency, newRate);
    }

    public double getRate(String currency) {
        return rates.getOrDefault(currency, 0.0);
    }
}

class CurrencyTrader implements IObserver {
    public void update(String currency, double newRate) {
        System.out.println("Трейдер: Курс валюты " + currency + " изменен на " + newRate);
    }
}

class CurrencyDisplay implements IObserver {
    public void update(String currency, double newRate) {
        System.out.println("Дисплей: Текущий курс " + currency + " - " + newRate);
    }
}

class AlertSystem implements IObserver {
    public void update(String currency, double newRate) {
        System.out.println("Система уведомлений: Внимание! Курс " + currency + " изменен на " + newRate);
    }
}


public class Main_2 {
    public static void main(String[] args) {
        CurrencyExchange exchange = new CurrencyExchange();
        CurrencyTrader trader = new CurrencyTrader();
        CurrencyDisplay display = new CurrencyDisplay();
        AlertSystem alert = new AlertSystem();

        exchange.attach(trader);
        exchange.attach(display);
        exchange.attach(alert);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите валюту для обновления курса (или 'exit' для выхода): ");
            String currency = scanner.nextLine();
            if ("exit".equalsIgnoreCase(currency)) {
                break;
            }

            System.out.print("Введите новый курс для " + currency + ": ");
            double newRate = scanner.nextDouble();
            scanner.nextLine();

            exchange.updateRate(currency, newRate);
        }

        scanner.close();
    }
}
