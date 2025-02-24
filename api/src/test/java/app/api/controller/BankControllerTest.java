package app.api.controller;

import core.application.port.in.TransactionUseCase;
import core.domain.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BankController.class)
@ExtendWith(MockitoExtension.class)
class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransactionUseCase transactionUseCase;

    @Test
    void shouldReturnBalance() throws Exception {
        when(transactionUseCase.getBalance()).thenReturn(100.50);

        mockMvc.perform(get("/balance"))
                .andExpect(status().isOk())
                .andExpect(content().string("100.5"));
    }

    @Test
    void shouldReturnTransactions() throws Exception {
        List<Transaction> transactions = List.of(
                new Transaction(50.0, "+", "Deposit", LocalDateTime.now()),
                new Transaction(20.0, "-", "Withdrawal", LocalDateTime.now())
        );
        when(transactionUseCase.getTransactions()).thenReturn(transactions);

        mockMvc.perform(get("/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void shouldReturnTransactionById() throws Exception {
        Long transactionId = 1L;
        Transaction transaction = new Transaction(100.0, "+", "Deposit", LocalDateTime.now());

        when(transactionUseCase.getTransaction(transactionId)).thenReturn(Optional.of(transaction));

        mockMvc.perform(get("/transaction/{id}", transactionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(100.0))
                .andExpect(jsonPath("$.operator").value("+"))
                .andExpect(jsonPath("$.label").value("Deposit"));
    }

    @Test
    void shouldDeleteTransaction() throws Exception {
        Long transactionId = 1L;

        doNothing().when(transactionUseCase).deleteTransaction(transactionId);

        mockMvc.perform(delete("/transaction/{id}", transactionId))
                .andExpect(status().isOk());

        verify(transactionUseCase, times(1)).deleteTransaction(transactionId);
    }
}
