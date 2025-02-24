package persistence.repository;

import core.domain.model.Transaction;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import persistence.entity.TransactionEntity;
import persistence.mapper.TransactionMapper;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TransactionRepositoryImplTest {

    @Mock
    private TransactionJpaRepository transactionJpaRepository;

    @InjectMocks
    private TransactionRepositoryImpl transactionRepository;

    @Test
    void shouldReturnCorrectBalance() {
        when(transactionJpaRepository.getTotalBalance()).thenReturn(100.0);
        double balance = transactionRepository.getBalanceData();
        assertEquals(100.0, balance);
    }

    @Test
    void shouldReturnLast10Transactions() {
        List<TransactionEntity> mockEntities = List .of(
                new TransactionEntity(1L, 10, "+", "Test", LocalDateTime.now())
        );

        when(transactionJpaRepository.findTop10ByOrderByIdDesc()).thenReturn(mockEntities);

        List<Transaction> transactions = transactionRepository.getTransactions();

        assertNotNull(transactions);
        assertEquals(1, transactions.size());
    }

    @Test
    void shouldSaveTransaction() {
        Transaction transaction = new Transaction(null, 20, "+", "Test Save", LocalDateTime.now());
        TransactionEntity entity = TransactionMapper.toEntity(transaction);

        when(transactionJpaRepository.save(any(TransactionEntity.class))).thenReturn(entity);

        Transaction savedTransaction = transactionRepository.newTransaction(transaction);

        assertNotNull(savedTransaction);
        assertEquals(20, savedTransaction.getAmount());
    }
}
