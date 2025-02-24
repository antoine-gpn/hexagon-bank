package core.application.port.in;

import core.domain.model.Transaction;
import java.util.Optional;
import java.util.List;

public interface TransactionUseCase {
    double getBalance();
    List<Transaction> getTransactions();
    Optional<Transaction> getTransaction(Long id);
    Transaction newTransaction(Transaction transaction);
    boolean deleteTransaction(Long id);
}

