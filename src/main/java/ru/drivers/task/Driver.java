package ru.drivers.task;

public class Driver implements Comparable<Driver>{
    private String name;
    private PaymentType paymentType;
    private int paymentRate;
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

    public void setName(String name) {
        this.name = name;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public int getPaymentRate() {
        return paymentRate;
    }

    public void setPaymentRate(int paymentRate) {
        this.paymentRate = paymentRate;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", monthlyPayment=" + monthlyPayment +
                '}';
    }

    @Override
    public int compareTo(Driver driver) {
        int result = (int)(driver.monthlyPayment - this.monthlyPayment);
        if (result == 0){
            result = this.name.compareTo(driver.name);
        }
        return result;
    }
}
