package persistence.repository;

import core.application.port.out.TransactionRepository;
import core.domain.model.Transaction;

import org.springframework.stereotype.Repository;
import persistence.entity.TransactionEntity;
import persistence.mapper.TransactionMapper;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    private final TransactionJpaRepository transactionRepository;

    public TransactionRepositoryImpl(TransactionJpaRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction load() {
        return new Transaction();
    }

    @Override
    public void save(Transaction account) {

    }

    @Override
    public double getBalanceData() {
        return transactionRepository.getTotalBalance();
    }

    @Override
    public List<Transaction> getTransactions() {
        List<TransactionEntity> entities = transactionRepository.findTop10ByOrderByIdDesc();
        return entities.stream()
                .map(TransactionMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Transaction> getTransaction(Long id) {
        return transactionRepository.findById(id).map(TransactionMapper::toDomain);
    }

    @Override
    public Transaction newTransaction(Transaction transaction) {
        TransactionEntity entity = TransactionMapper.toEntity(transaction);
        TransactionEntity savedEntity = transactionRepository.save(entity);
        return TransactionMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteTransaction(Long id) {
        Optional<TransactionEntity> transaction = transactionRepository.findById(id);

        if (transaction.isPresent()) {
            transactionRepository.delete(transaction.get());
        } else {
            throw new IllegalArgumentException("Transaction with ID " + id + "does not exist");
        }


    }


}
