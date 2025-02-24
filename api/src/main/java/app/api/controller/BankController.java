package app.api.controller;

import core.application.port.in.TransactionUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.*;
import core.domain.model.Transaction;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Transactions", description = "Financial transaction management")
public class BankController {

    @Autowired
    private final TransactionUseCase transactionUseCase;

    public BankController(TransactionUseCase transactionUseCase) {
        this.transactionUseCase = transactionUseCase;
    }

    @GetMapping("/")
    @Operation(summary = "Redirect to documentation")
    public RedirectView getDoc() {
        return new RedirectView("/swagger-ui/index.html");
    }

    @GetMapping("/balance")
    @Operation(summary = "Retrieve account balance")
    public ResponseEntity<Double> getBalance() {
        try {
            double balance = transactionUseCase.getBalance();

            return ResponseEntity.ok(balance);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/transactions")
    @Operation(summary = "Retrieve all transactions")
    public ResponseEntity<List<Transaction>> getTransactions() {
        List<Transaction> transactions = transactionUseCase.getTransactions();

        if (transactions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/transaction/{id}")
    @Operation(summary = "Retrieve a transaction via ID")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        return transactionUseCase.getTransaction(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/transaction")
    @Operation(summary = "Create a new transaction")
    public ResponseEntity<Transaction> newTransaction(@RequestBody Transaction transaction) {
        try {
            Transaction createdTransaction = transactionUseCase.newTransaction(transaction);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/transaction/{id}")
    @Operation(summary = "Delete a transaction")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        try {
            boolean deleted = transactionUseCase.deleteTransaction(id);

            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
