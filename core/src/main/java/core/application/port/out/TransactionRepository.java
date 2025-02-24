package core.application.port.out;

import core.domain.model.Transaction;
import java.util.Optional;
import java.util.List;

public interface TransactionRepository {
    Transaction load();
    void save(Transaction transaction);

    double getBalanceData();

    List<Transaction> getTransactions();

    Optional<Transaction> getTransaction(Long id);

    Transaction newTransaction(Transaction transaction);
    void deleteTransaction(Long id);
}