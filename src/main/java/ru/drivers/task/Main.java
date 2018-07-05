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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILENAME ="drivers.json";

    public static void main(String[] args) {
        List<Driver> driversForJson = getDriversList();
        JsonHelper.jsonWrite(FILENAME, driversForJson);

        System.out.println("Reading driver's list from json");
        List<Driver> drivers = JsonHelper.jsonRead(FILENAME);
        drivers.stream().forEach(System.out::println);

        driverSort(drivers);

        Scanner reader = new Scanner(System.in);
        while (true){
            System.out.println();
            System.out.println("Enter command:");
            System.out.println("1 - top 5");
            System.out.println("2 - last 3");
            System.out.println("3 - new driver");
            System.out.println("4 - exit");
            String command = reader.nextLine();
            if(command.equals("4")){
                break;
            }
            switch (command){
                case "1":
                    top5drivers(drivers);
                    break;
                case "2":
                    last3drivers(drivers);
                    break;
                case "3":
                    addDriver(drivers, reader);
                    break;
                case "4":
                    return;
            }
        }
    }

    public static List<Driver> getDriversList() {
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
        return driversForJson;
    }

    public static void driverSort(List<Driver> drivers) {
        drivers.sort((d1, d2) -> {
            int result = (int)(d2.getMonthlyPayment() - d1.getMonthlyPayment());
            if (result == 0){
                result = d1.getName().compareTo(d2.getName());
            }
            return result;
        });
        System.out.println();
        System.out.println("Sorted list of drivers");
        drivers.stream().forEach(System.out::println);
    }

    public static void last3drivers(List<Driver> drivers) {
        System.out.println();
        System.out.println("Last 3 drivers");
        for(int i = drivers.size()-1; i >drivers.size()-4; i--){
            System.out.println(drivers.get(i));
        }
    }

    public static void top5drivers(List<Driver> drivers) {
        System.out.println();
        System.out.println("Top 5 drivers");
        for(int i = 0; i <5; i++){
            System.out.println(drivers.get(i));
        }
    }
    public static void addDriver(List<Driver> drivers, Scanner reader){
        System.out.print("Input driver's name:");
        String name = reader.nextLine();

        System.out.print("Input payment type (fixed, hourly):");
        String strPaymentType = reader.nextLine();
        PaymentType paymentType = null;
        if(strPaymentType.equals("fixed")){
            paymentType = PaymentType.FIXED;
        }
        else {
            paymentType = PaymentType.HOURLY;
        }

        System.out.print("Input payment rate:");
        int paymentRate = Integer.parseInt(reader.nextLine());

        drivers.add(new Driver(name, paymentType, paymentRate));
        driverSort(drivers);
        JsonHelper.jsonWrite(FILENAME, drivers);
    }
}
