package core.domain.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private Long id;
    private double amount;
    private String operator;
    private String label;
    private LocalDateTime date;

    public Transaction(double amount, String operator, String label, LocalDateTime date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.amount = amount;
        this.operator = operator;
        this.label = label;
        this.date = date;
    }
}
