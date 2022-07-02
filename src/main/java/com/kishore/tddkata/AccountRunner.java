package com.kishore.tddkata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Clock clock = new Clock();
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        Console console = new Console();
        StatementPrinter statementWriter = new StatementPrinter(console);
        Account account = new Account(transactionRepository, statementWriter);
        account.deposit(1000);
        account.withdraw(100);
        account.deposit(500);
        account.printStatement();
    }
}
