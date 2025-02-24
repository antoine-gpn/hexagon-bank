package persistence.mapper;

import core.domain.model.Transaction;
import persistence.entity.TransactionEntity;

public class TransactionMapper {
    public static Transaction toDomain(TransactionEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Transaction(entity.getId(), entity.getAmount(), entity.getOperator(), entity.getLabel(), entity.getDate());
    }

    public static TransactionEntity toEntity(Transaction transaction) {
        if (transaction == null) {
            return null;
        }
        return new TransactionEntity(transaction.getId(), transaction.getAmount(), transaction.getOperator(), transaction.getLabel(), transaction.getDate());
    }
}
