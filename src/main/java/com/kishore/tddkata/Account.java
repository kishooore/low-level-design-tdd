package com.kishore.tddkata;

import java.util.List;

public class Account {

    private final TransactionRepository transactionRepository;

    private final StatementPrinter statementPrinter;

    public Account(TransactionRepository transactionRepository, StatementPrinter statementPrinter) {
        this.transactionRepository = transactionRepository;
        this.statementPrinter = statementPrinter;
    }

    public void printStatement() {
        List<Transaction> transactions = transactionRepository.allTransactions();
        statementPrinter.print(transactions);
    }

    public void deposit(double amount) {
        transactionRepository.addDeposit(amount);
    }

    public void withdraw(double amount) {
        transactionRepository.addWithdrawal(amount);
    }
}
