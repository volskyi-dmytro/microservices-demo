package com.stpunk47.accounts.functions;

import com.stpunk47.accounts.service.IAccountsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountsFunctions {


    private static final Logger logger = LoggerFactory.getLogger(AccountsFunctions.class);

    @Bean
    public Consumer<Long> updateCommunication(IAccountsService accountsService) {
        return accountNumber -> {
                logger.info("Updating communication for account number: {}", accountNumber.toString());
        accountsService.updateCommunicationStatus(accountNumber);
    };
    }
}
