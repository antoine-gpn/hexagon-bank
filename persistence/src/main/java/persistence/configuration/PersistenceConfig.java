package persistence.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "persistence.repository")
@EntityScan(basePackages = "persistence.entity")
@ComponentScan(basePackages = "persistence")
public class PersistenceConfig {
}
