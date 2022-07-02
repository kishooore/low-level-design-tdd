package com.kishore.tddkata;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountShould {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private StatementPrinter statementPrinter;

    private Account account;

    @BeforeEach
    public void setup() {
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    void store_a_deposit_transaction() {
        account.deposit(1000.00);
        verify(transactionRepository).addDeposit(1000.00);
    }

    @Test
    void store_a_withdraw_transaction() {
        account.withdraw(100.00);
        verify(transactionRepository).addWithdrawal(100.00);
    }

    @Test
    void print_a_statement() {
        List<Transaction> transactions = List.of(new Transaction("12/05/2015",100.00));
        given(transactionRepository.allTransactions()).willReturn(transactions);
        account.printStatement();
        verify(statementPrinter).print(transactions);
    }
}
