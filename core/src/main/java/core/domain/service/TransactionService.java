package core.domain.service;

import core.application.port.out.TransactionRepository;
import core.application.port.in.TransactionUseCase;
import core.domain.model.Transaction;
import java.util.Optional;
import java.util.List;

public class TransactionService implements TransactionUseCase {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public double getBalance() {
        return repository.getBalanceData();
    }

    @Override
    public List<Transaction> getTransactions() {
        return repository.getTransactions();
    }

    @Override
    public Optional<Transaction> getTransaction(Long id) {
        return repository.getTransaction(id);
    }

    @Override
    public Transaction newTransaction(Transaction transaction) {
        double amount = transaction.getAmount();
        double balance = repository.getBalanceData();

        if (transaction.getOperator().equals("-") && amount > balance) {
            throw new IllegalArgumentException("Insufficient funds for this transaction");
        }

        return repository.newTransaction(transaction);
    }

    @Override
    public boolean deleteTransaction(Long id) {
        if (repository.getTransaction(id).isEmpty()) {
           return false;
        }
        repository.deleteTransaction(id);
        return true;
    }

}
