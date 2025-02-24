package persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import persistence.entity.TransactionEntity;
import java.util.List;

@Repository
public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Long> {

    @Query("SELECT COALESCE(SUM(CASE WHEN t.operator = '+' THEN t.amount ELSE -t.amount END), 0) FROM TransactionEntity t")
    double getTotalBalance();

    List<TransactionEntity> findTop10ByOrderByIdDesc();
}
