package ru.drivers.task;

import java.util.function.Function;

public enum PaymentType {
    FIXED(x -> x),
    HOURLY(x -> 20.8 * 8 * x);

    PaymentType(Function<Double, Double> monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    private Function<Double, Double> monthlyPayment;

    public Double getMonthlyPayment(Double paymentRate) {
        return this.monthlyPayment.apply(paymentRate);
    }
}