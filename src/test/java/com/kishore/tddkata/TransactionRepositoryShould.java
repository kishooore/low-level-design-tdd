package com.kishore.tddkata;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionRepositoryShould {

    public static final String TODAY = "12/05/2015";
    private TransactionRepository transactionRepository;

    @Mock
    private Clock clock;

    @BeforeEach
    public void setup () {
        transactionRepository = new TransactionRepository(clock);
    }

    @Test
    void create_and_store_a_deposit_transaction() {
        given(clock.todayAsString()).willReturn(TODAY);

        transactionRepository.addDeposit(100.00);

        List<Transaction> transactions = transactionRepository.allTransactions();
        assertThat(transactions.size()).isOne();
        assertThat(transactions.get(0)).isEqualTo(transaction(TODAY, 100.00));
    }

    @Test
    void create_and_store_a_withdrawal_transaction() {
        given(clock.todayAsString()).willReturn(TODAY);

        transactionRepository.addWithdrawal(100.00);

        List<Transaction> transactions = transactionRepository.allTransactions();
        assertThat(transactions.size()).isOne();
        assertThat(transactions.get(0)).isEqualTo(transaction(TODAY, -100.00));
    }
    private Transaction transaction(String date, double amount) {
        return new Transaction(date, amount);
    }
}
