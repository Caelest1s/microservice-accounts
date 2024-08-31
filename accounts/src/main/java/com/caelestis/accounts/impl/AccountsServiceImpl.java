package com.caelestis.accounts.impl;

import com.caelestis.accounts.dto.CustomerDto;
import com.caelestis.accounts.repository.AccountsRepository;
import com.caelestis.accounts.repository.CustomerRepository;
import com.caelestis.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

    }
}
