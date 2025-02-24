package persistence.mapper;

import core.domain.model.Transaction;
import org.junit.jupiter.api.Test;
import persistence.entity.TransactionEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionMapperTest {

    @Test
    void shouldMapEntityToDomainCorrectly() {
        TransactionEntity entity = new TransactionEntity(1L, 50, "+", "Test", LocalDateTime.now());

        Transaction transaction = TransactionMapper.toDomain(entity);

        assertNotNull(transaction);
        assertEquals(entity.getId(), transaction.getId());
        assertEquals(entity.getAmount(), transaction.getAmount());
    }

    @Test
    void shouldMapDomainToEntityCorrectly() {
        Transaction transaction = new Transaction(1L, 50, "+", "Test", LocalDateTime.now());

        TransactionEntity entity = TransactionMapper.toEntity(transaction);

        assertNotNull(entity);
        assertEquals(transaction.getId(), entity.getId());
        assertEquals(transaction.getAmount(), entity.getAmount());
    }
}

