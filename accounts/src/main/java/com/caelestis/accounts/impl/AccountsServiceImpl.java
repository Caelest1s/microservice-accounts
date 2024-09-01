package com.caelestis.accounts.impl;

import com.caelestis.accounts.contants.AccountsConstants;
import com.caelestis.accounts.dto.CustomerDto;
import com.caelestis.accounts.entity.Accounts;
import com.caelestis.accounts.entity.Customer;
import com.caelestis.accounts.exception.CustomerAlreadyExistsException;
import com.caelestis.accounts.mapper.CustomerMapper;
import com.caelestis.accounts.repository.AccountsRepository;
import com.caelestis.accounts.repository.CustomerRepository;
import com.caelestis.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException(
                    "Customer already registered with given mobileNumber " + customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long accNumber = randomNumber();

        newAccount.setAccountNumber(accNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }

    private Long randomNumber(){
        return 1000000000L + new Random().nextInt(900000000);
    }
}
