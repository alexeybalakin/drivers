/*
22. Есть два вида водителей:
- с почасовой оплатой
- фиксированной оплатой.
Для каждого вида необходимо рассчитывать заработную плату. Для «повременщиков» формула для расчета такова:
«среднемесячная заработная плата = 20.8 * 8 * почасовую ставку», для работников с фиксированной оплатой «среднемесячная заработная плата = фиксированной месячной оплате».

Необходимо:
загружать список водителей из файла
Упорядочить последовательность водителей по убыванию среднемесячного заработка.
 При совпадении зарплаты – упорядочивать данные по ФИО в лексикографическом порядке.
Выводить ФИО и среднемесячный заработок для всех элементов списка.
Вывести пять первых элемента из списка
 Вывести три последних элемента из списка
Приветствуется:
организация ввода-вывода данных о новом водителе во время работы программы
проверка входного файла на ошибки

 */


package ru.drivers.task;

import java.util.*;

public class Main {
    private static final String FILENAME = "drivers.json";

    public static void main(String[] args) {
        List<Driver> drivers = new ArrayList<>();
        Scanner reader = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Enter command:");
            System.out.println("1 - prepare driver list file");
            System.out.println("2 - load drivers from file");
            System.out.println("3 - write drivers to file");
            System.out.println("4 - top 5");
            System.out.println("5 - last 3");
            System.out.println("6 - new driver");
            System.out.println("7 - exit");
            String command = reader.nextLine();
            if (command.equals("7")) {
                break;
            }
            switch (command) {
                case "1":
                    System.out.println("Preparing driver's list file");
                    prepareDriverListFile(FILENAME);
                    break;
                case "2":
                    System.out.println("Reading driver's list from json");
                    drivers = loadDrivers(FILENAME);
                    driverSort(drivers);
                    drivers.stream().forEach(System.out::println);
                    break;
                case "3":
                    System.out.println("Writing driver's list to json");
                    writeDrivers(FILENAME, drivers);
                    break;
                case "4":
                    top5drivers(drivers);
                    break;
                case "5":
                    last3drivers(drivers);
                    break;
                case "6":
                    addDriver(drivers, reader);
                    driverSort(drivers);
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Command not recognized");
                    break;
            }
        }
    }

    public static List<Driver> loadDrivers(String filename) {
        return JsonHelper.jsonRead(filename);
    }

    public static void writeDrivers(String filename, List<Driver> drivers) {
        JsonHelper.jsonWrite(filename, drivers);
    }

    public static void prepareDriverListFile(String filename) {
        List<Driver> driversForJson = new ArrayList<>();
        driversForJson.add(new Driver("John Snow", PaymentType.FIXED, 1000));
        driversForJson.add(new Driver("Ivan Petrov", PaymentType.FIXED, 1000));
        driversForJson.add(new Driver("Tom Sawyer", PaymentType.FIXED, 500));
        driversForJson.add(new Driver("Boris Johnson", PaymentType.HOURLY, 5));
        driversForJson.add(new Driver("Hubert Farnsworth", PaymentType.HOURLY, 8));
        driversForJson.add(new Driver("Bender Rodriguez", PaymentType.HOURLY, 10));
        driversForJson.add(new Driver("Philip Fry", PaymentType.FIXED, 500));
        driversForJson.add(new Driver("Fedor Sumkin", PaymentType.FIXED, 750));
        driversForJson.add(new Driver("Jhon Smith", PaymentType.HOURLY, 8));
        JsonHelper.jsonWrite(filename, driversForJson);
    }

    public static void driverSort(List<Driver> drivers) {
        drivers.sort(Comparator.comparing(Driver::getMonthlyPayment).reversed().thenComparing(Driver::getName));
    }

    public static void last3drivers(List<Driver> drivers) {
        System.out.println();
        System.out.println("Last 3 drivers");
        drivers.subList(drivers.size()-3, drivers.size()).forEach(System.out::println);
    }

    public static void top5drivers(List<Driver> drivers) {
        System.out.println();
        System.out.println("Top 5 drivers");
        drivers.subList(0,5).forEach(System.out::println);
    }

    public static void addDriver(List<Driver> drivers, Scanner reader) {
        System.out.print("Input driver's name:");
        String name = reader.nextLine();

        PaymentType paymentType = null;
        while(paymentType == null) {
        System.out.print("Input payment type (fixed, hourly):");
        String strPaymentType = reader.nextLine();
            if (strPaymentType.equals("fixed")) {
                paymentType = PaymentType.FIXED;
            } else if (strPaymentType.equals("hourly")) {
                paymentType = PaymentType.HOURLY;
            }
        }
        System.out.print("Input payment rate:");
        int paymentRate = Integer.parseInt(reader.nextLine());

        drivers.add(new Driver(name, paymentType, paymentRate));
    }
}
