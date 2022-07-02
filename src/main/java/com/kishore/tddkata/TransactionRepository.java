package com.kishore.tddkata;

import static java.util.Collections.unmodifiableList;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private Clock clock;
    private List<Transaction> transactions = new ArrayList<>();

    public TransactionRepository(Clock clock) {
        this.clock = clock;
    }

    public void addDeposit(double amount) {
        transactions.add(new Transaction(clock.todayAsString(), amount));
    }

    public void addWithdrawal(double amount) {
        transactions.add(new Transaction(clock.todayAsString(), -1 * amount));
    }

    public List<Transaction> allTransactions() {
        return unmodifiableList(transactions);
    }
}
