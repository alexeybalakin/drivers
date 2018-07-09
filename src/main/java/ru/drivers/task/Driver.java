package ru.drivers.task;


public final class Driver {
    private String name;
    private PaymentType paymentType;
    private double paymentRate;
    private double monthlyPayment;

    public Driver(String name, PaymentType paymentType, double paymentRate) {
        this.name = name;
        this.paymentType = paymentType;
        this.paymentRate = paymentRate;
        this.monthlyPayment = paymentType.getMonthlyPayment().apply(paymentRate);
    }

    public String getName() {
        return name;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public double getPaymentRate() {
        return paymentRate;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", monthlyPayment=" + monthlyPayment +
                '}';
    }
}
