package com.kishore.tddkata;

import java.util.Objects;

public class Transaction {
    private final double amount;
    private final String date;

    public Transaction(String date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, date);
    }

    public double amount() {
        return this.amount;
    }

    public String date() {
        return this.date;
    }
}
