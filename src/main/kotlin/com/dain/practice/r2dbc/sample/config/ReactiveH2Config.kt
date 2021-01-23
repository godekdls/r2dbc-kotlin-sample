package com.dain.practice.r2dbc.sample.config

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@Configuration
@EnableR2dbcRepositories(basePackages = ["com.dain.practice.r2dbc.sample.repositories"])
class ReactiveH2Config : AbstractR2dbcConfiguration() {

    @Bean
    override fun connectionFactory(): ConnectionFactory {
        return ConnectionFactories.get("r2dbc:h2:mem:///testdb?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
    }

    @Bean
    fun connectionFactoryInitializer(connectionFactory: ConnectionFactory) =
        ConnectionFactoryInitializer().apply {
            this.setConnectionFactory(connectionFactory)
            this.setDatabasePopulator(
                CompositeDatabasePopulator().apply {
                    this.addPopulators(
                        ResourceDatabasePopulator(ClassPathResource("schema.sql")),
                        ResourceDatabasePopulator(ClassPathResource("data.sql"))
                    )
                }
            )
        }
}