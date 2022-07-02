package com.kishore.tddkata;

import static org.mockito.BDDMockito.given;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PrintStatementFeature {
    @Mock
    private Console console;
    @Mock
    private Clock clock;
    private Account account;

    @BeforeEach
    public void setup() {
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        StatementPrinter statementPrinter = new StatementPrinter(console);
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    void print_statement_should_print_all_transactions() {
        given(clock.todayAsString()).willReturn("01/06/2022", "02/06/2022", "03/06/2022");
        account.deposit(1000.00);
        account.withdraw(100.00);
        account.deposit(500.00);
        account.printStatement();
        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("03/06/2022 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("02/06/2022 | -100.00 | 900.00");
        inOrder.verify(console).printLine("01/06/2022 | 1000.00 | 1000.00");
    }
}
