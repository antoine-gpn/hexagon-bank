package app.api.configuration;

import core.application.port.in.TransactionUseCase;
import core.application.port.out.TransactionRepository;
import core.domain.service.TransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {

    @Bean
    public TransactionUseCase transactionUseCase(TransactionRepository repository) {
        return new TransactionService(repository);
    }
}
