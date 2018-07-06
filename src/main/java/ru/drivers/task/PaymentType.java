package ru.drivers.task;

import java.util.function.Function;

public enum PaymentType {
    FIXED(x -> x),
    HOURLY(x -> 20.8 * 8 * x);

    PaymentType(Function<Double, Double> monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    Function<Double, Double> monthlyPayment;
}
