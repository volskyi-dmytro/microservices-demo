package com.stpunk47.message.functions;

import com.stpunk47.message.dto.AccountsMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    private static final Logger logger = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Function<AccountsMessageDto, AccountsMessageDto> email() {
       return accountsMessageDto -> {
           logger.info("Sending email with details: {}", accountsMessageDto.toString());
         return accountsMessageDto;
       };
    }

    @Bean
    public Function<AccountsMessageDto, Long> sms() {
        return accountsMessageDto -> {
            logger.info("Sending sms with details: {}", accountsMessageDto.toString());
            return accountsMessageDto.accountNumber();
        };
    }

}
