package com.caelestis.accounts.service.impl;

import com.caelestis.accounts.dto.AccountsDto;
import com.caelestis.accounts.dto.CardsDto;
import com.caelestis.accounts.dto.CustomerDetailsDto;
import com.caelestis.accounts.dto.LoansDto;
import com.caelestis.accounts.entity.Accounts;
import com.caelestis.accounts.entity.Customer;
import com.caelestis.accounts.exception.ResourceNotFoundException;
import com.caelestis.accounts.mapper.AccountsMapper;
import com.caelestis.accounts.mapper.CustomerMapper;
import com.caelestis.accounts.repository.AccountsRepository;
import com.caelestis.accounts.repository.CustomerRepository;
import com.caelestis.accounts.service.ICustomerService;
import com.caelestis.accounts.service.client.CardsFeignClient;
import com.caelestis.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

    private CustomerRepository customerRepository;
    private AccountsRepository accountsRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto;
        customerDetailsDto = assembleCustomerDetailsDto(customer, account, mobileNumber);
        return customerDetailsDto;
    }

    private CustomerDetailsDto assembleCustomerDetailsDto(Customer customer, Accounts account, String mobileNumber) {
        CustomerDetailsDto entity;
        entity = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        entity.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));
        entity.setLoansDto(getLoanDetailsFeign(mobileNumber));
        entity.setCardsDto(getCardDetailsFeign(mobileNumber));
        return entity;
    }

    private LoansDto getLoanDetailsFeign(String mobileNumber) {
        ResponseEntity<LoansDto> loanDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        return loanDtoResponseEntity.getBody();
    }

    private CardsDto getCardDetailsFeign(String mobileNumber) {
        ResponseEntity<CardsDto> cardDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        return cardDtoResponseEntity.getBody();
    }
}
