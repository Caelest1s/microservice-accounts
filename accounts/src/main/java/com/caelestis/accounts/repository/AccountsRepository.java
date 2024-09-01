package com.caelestis.accounts.repository;

import com.caelestis.accounts.entity.Accounts;
import com.caelestis.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

}