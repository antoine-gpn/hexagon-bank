package core.domain.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

class TransactionTest {

    @Test
    void shouldCreateTransactionSuccessfully() {
        Transaction transaction = new Transaction(10.5, "+", "Payment", LocalDateTime.now());

        assertNotNull(transaction);
        assertTrue(transaction.getAmount() > 0);
        assertEquals("+", transaction.getOperator());
        assertEquals("Payment", transaction.getLabel());
        assertNotNull(transaction.getDate());
    }

    @Test
    void shouldThrowExceptionForInvalidAmount() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Transaction(-1.2,"+", "Payment", LocalDateTime.now()));

        assertEquals("Amount must be positive", exception.getMessage());
    }
}
