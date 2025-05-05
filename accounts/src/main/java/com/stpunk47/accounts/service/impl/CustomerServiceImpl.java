package com.stpunk47.accounts.service.impl;

import com.stpunk47.accounts.dto.AccountsDto;
import com.stpunk47.accounts.dto.CardsDto;
import com.stpunk47.accounts.dto.CustomerDetailsDto;
import com.stpunk47.accounts.dto.LoansDto;
import com.stpunk47.accounts.entity.Accounts;
import com.stpunk47.accounts.entity.Customer;
import com.stpunk47.accounts.exception.ResourceNotFoundException;
import com.stpunk47.accounts.mapper.AccountsMapper;
import com.stpunk47.accounts.mapper.CustomerMapper;
import com.stpunk47.accounts.repositories.AccountsRepository;
import com.stpunk47.accounts.repositories.CustomerRepository;
import com.stpunk47.accounts.service.ICustomerService;
import com.stpunk47.accounts.service.client.CardsFeignClient;
import com.stpunk47.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
