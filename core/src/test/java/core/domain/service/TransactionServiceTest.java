package core.domain.service;

import core.application.port.out.TransactionRepository;
import core.domain.model.Transaction;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnBalance() {
        when(transactionRepository.getBalanceData()).thenReturn(10.0);

        double balance = transactionService.getBalance();

        assertEquals(10.0, balance);
        verify(transactionRepository, times(1)).getBalanceData();
    }

    @Test
    void shouldReturnTransactions() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(50.0, "+", "Deposit", LocalDateTime.now()),
                new Transaction(20.0, "-", "Withdrawal", LocalDateTime.now())
        );
        when(transactionRepository.getTransactions()).thenReturn(transactions);

        List<Transaction> result = transactionService.getTransactions();

        assertEquals(2, result.size());
        verify(transactionRepository, times(1)).getTransactions();
    }

    @Test
    void shouldReturnTransaction() {
        Long id = 1L;
        Transaction transaction = new Transaction(id, 10.0, "+", "Deposit", LocalDateTime.now());
        when(transactionRepository.getTransaction(id)).thenReturn(Optional.of(transaction));

        Optional<Transaction> result = transactionService.getTransaction(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals(10.0, result.get().getAmount());
        assertEquals("+", result.get().getOperator());
        assertEquals("Deposit", result.get().getLabel());
        verify(transactionRepository, times(1)).getTransaction(id);
    }

    @Test
    void shouldSaveNewTransaction() {
        Transaction transaction = new Transaction(30.0, "+", "Transfer", LocalDateTime.now());
        when(transactionRepository.newTransaction(transaction)).thenReturn(transaction);

        Transaction result = transactionService.newTransaction(transaction);

        assertNotNull(result);
        assertEquals(30.0, result.getAmount());
        assertEquals("+", result.getOperator());
        assertEquals("Transfer", result.getLabel());
        verify(transactionRepository, times(1)).newTransaction(transaction);
    }

    @Test
    void shouldThrowExceptionWhenInsufficientFunds() {
        when(transactionRepository.getBalanceData()).thenReturn(50.0);

        Transaction transaction = new Transaction(60.0, "-", "Withdrawal", LocalDateTime.now());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> transactionService.newTransaction(transaction));

        assertEquals("Insufficient funds for this transaction", exception.getMessage());
        verify(transactionRepository, never()).newTransaction(any());
    }

    @Test
    void shouldDeleteTransactionSuccessfully() {
        Long id = 1L;
        Transaction transaction = new Transaction(id, 10.0, "+", "Test", LocalDateTime.now());

        when(transactionRepository.getTransaction(id)).thenReturn(Optional.of(transaction));

        transactionService.deleteTransaction(id);
        verify(transactionRepository, times(1)).deleteTransaction(id);
    }

    @Test
    void shouldThrowExceptionWhenTransactionDoesNotExist() {
        Long id = 1L;

        when(transactionRepository.getTransaction(id)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                transactionService.deleteTransaction(id)
        );

        assertEquals("Transaction with ID " + id + " does not exist.", exception.getMessage());
        verify(transactionRepository, never()).deleteTransaction(id);
    }

}
