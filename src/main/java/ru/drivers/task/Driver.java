package ru.drivers.task;


public final class Driver {
    private String name;
    private PaymentType paymentType;
    private double paymentRate;
    private double monthlyPayment;

    public Driver(String name, PaymentType paymentType, int paymentRate) {
        this.name = name;
        this.paymentType = paymentType;
        this.paymentRate = paymentRate;
        setMonthlyPayment(paymentType, paymentRate);
    }

    private void setMonthlyPayment(PaymentType paymentType, int paymentRate){
        if(paymentType == PaymentType.FIXED){
            monthlyPayment = paymentRate;
        }
        else {
            monthlyPayment = 20.8 * 8 * paymentRate;
        }
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
