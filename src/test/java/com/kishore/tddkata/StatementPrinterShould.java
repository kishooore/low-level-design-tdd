package com.kishore.tddkata;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StatementPrinterShould {

    @Mock
    private Console console;
    private StatementPrinter statementPrinter;

    @BeforeEach
    void setup() {
        this.statementPrinter = new StatementPrinter(console);
    }

    @Test
    void always_print_the_header() {
        List<Transaction> NO_TRANSACTIONS = List.of();
        statementPrinter.print(NO_TRANSACTIONS);
        verify(console).printLine("DATE | AMOUNT | BALANCE");
    }

    @Test
    void print_transactions_in_reverse_chronological_order() {
        List<Transaction> transactions = transactionContaining(
            deposit("01/05/2015", 1000.00),
            withdrawal("02/05/2015", 100.00),
            deposit("10/05/2015", 500.00)
        );
        statementPrinter.print(transactions);
        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/05/2015 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("02/05/2015 | -100.00 | 900.00");
        inOrder.verify(console).printLine("01/05/2015 | 1000.00 | 1000.00");
    }

    private List<Transaction> transactionContaining(Transaction... transactions) {
        return Arrays.asList(transactions);
    }

    private Transaction withdrawal(String date, double amount) {
        return new Transaction(date, -amount);
    }

    private Transaction deposit(String date, double amount) {
        return new Transaction(date, amount);
    }
}
